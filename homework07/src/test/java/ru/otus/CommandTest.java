package ru.otus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.command.HardStopCommand;
import ru.otus.command.HelloWorldCommand;
import ru.otus.ioc.FunctionWithObjects;
import ru.otus.ioc.IoC;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommandTest {

    private GameObject gameObject;
    private ByteArrayOutputStream baos;


    @BeforeEach
    void init() {
        gameObject = new GameObject();
        var queueHandlers = new ArrayList<QueueHandler>();
        IoC.resolve("IoC.Register", "queueHandlers", (FunctionWithObjects) (args) -> queueHandlers);
        baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
    }

    @Test
    void shouldStartThreads() throws InterruptedException {
        var handlers = (ArrayList<QueueHandler>) IoC.resolve("queueHandlers");
        int count = handlers.size();

        ((Command) IoC.resolve("IoC.AddNewThread", gameObject)).execute();
        Assertions.assertEquals(count + 1, handlers.size());

        AtomicBoolean lock = handlers.get(0).getLock();
        for (int i = 0; i < 3; i++) {
            addCommand(handlers, lock, new HelloWorldCommand());
        }

        System.out.flush();
        String s = baos.toString();
        Assertions.assertLinesMatch(
                List.of("Hello World\nHello World\nHello World\n".split("\n")),
                List.of(s.split("\n"))
        );
    }

    @Test
    void shouldHardStopThread() throws InterruptedException {
        var handlers = (ArrayList<QueueHandler>) IoC.resolve("queueHandlers");
        ((Command) IoC.resolve("IoC.AddNewThread", gameObject)).execute();
        AtomicBoolean lock = handlers.get(0).getLock();

        addCommand(handlers, lock, new HelloWorldCommand());
        addCommand(handlers, lock, new HardStopCommand(handlers.get(0)));
        addCommand(handlers, lock, new HelloWorldCommand());

        System.out.flush();
        String s = baos.toString();
        Assertions.assertLinesMatch(
                List.of("Hello World\n".split("\n")),
                List.of(s.split("\n"))
        );
    }

    private static void addCommand(ArrayList<QueueHandler> handlers, AtomicBoolean lock, Command command) throws InterruptedException {
        synchronized (lock) {
            while (lock.get()) {
                lock.wait();
            }
            handlers.get(0).addCommand(command);

            lock.set(true);
            lock.notifyAll();

            /*while (lock.get()) {
                lock.wait();
            }*/
        }
    }
}
