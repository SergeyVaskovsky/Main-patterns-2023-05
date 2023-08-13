package ru.otus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.command.HelloWorldCommand;
import ru.otus.ioc.IoC;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommandTest {

    private GameObject gameObject;
    private ByteArrayOutputStream baos;


    @BeforeEach
    void init() {
        gameObject = new GameObject();
        baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
    }

    @Test
    void shouldStartThreads() throws InterruptedException {
        int count = gameObject.getQueueHandlers().size();

        ((Command) IoC.resolve("IoC.AddNewThread", gameObject)).execute();
        Assertions.assertEquals(count + 1, gameObject.getQueueHandlers().size());

        AtomicBoolean lock = gameObject.getQueueHandlers().get(0).getLock();
        for (int i = 0; i < 3; i++) {
            synchronized (lock) {
                while (lock.get()) {
                    lock.wait();
                }
                gameObject.getQueueHandlers().get(0).addCommand(new HelloWorldCommand());

                lock.set(true);
                lock.notifyAll();

                while (lock.get()) {
                    lock.wait();
                }
            }
        }

        System.out.flush();
        String s = baos.toString();
        Assertions.assertLinesMatch(
                List.of("Hello World\nHello World\nHello World\n".split("\n")),
                List.of(s.split("\n"))
        );
    }
}
