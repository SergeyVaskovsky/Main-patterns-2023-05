package ru.otus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.command.HardStopCommand;
import ru.otus.command.HelloWorldCommand;
import ru.otus.command.SoftStopCommand;
import ru.otus.ioc.FunctionWithObjects;
import ru.otus.ioc.IoC;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class CommandTest {

    private GameObject gameObject;
    private ByteArrayOutputStream baos;

    private static void addCommand(QueueHandler handler, Command command) {
        handler.addCommand(command);
    }

    @BeforeEach
    void init() {
        gameObject = new GameObject();
        IoC.resolve("Scopes.Current.Set", "root");
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


        for (int i = 0; i < 3; i++) {
            addCommand(handlers.get(0), new HelloWorldCommand());
        }
        addCommand(handlers.get(0), new HardStopCommand(handlers.get(0)));

        gameObject.getThread().join();

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

        addCommand(handlers.get(0), new HelloWorldCommand());
        addCommand(handlers.get(0), new HardStopCommand(handlers.get(0)));
        addCommand(handlers.get(0), new HelloWorldCommand());

        gameObject.getThread().join();

        System.out.flush();
        String s = baos.toString();
        Assertions.assertLinesMatch(
                List.of("Hello World\n".split("\n")),
                List.of(s.split("\n"))
        );
    }

    @Test
    void shouldSoftStopThread() throws InterruptedException {

        var handlers = (ArrayList<QueueHandler>) IoC.resolve("queueHandlers");
        ((Command) IoC.resolve("IoC.AddNewThread", gameObject)).execute();


        addCommand(handlers.get(0), new HelloWorldCommand());
        addCommand(handlers.get(0), new SoftStopCommand(handlers.get(0)));
        addCommand(handlers.get(0), new HelloWorldCommand());

        gameObject.getThread().join();

        System.out.flush();
        String s = baos.toString();
        Assertions.assertLinesMatch(
                List.of("Hello World\nHello World\n".split("\n")),
                List.of(s.split("\n"))
        );
    }

}
