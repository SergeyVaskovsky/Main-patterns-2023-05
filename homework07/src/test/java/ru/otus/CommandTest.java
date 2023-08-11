package ru.otus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.command.HelloWorldCommand;
import ru.otus.ioc.IoC;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

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

            synchronized (gameObject.getQueueHandlers().get(0).getLock()) {
                //System.out.println("enter sync test");
                while (gameObject.getQueueHandlers().get(0).getIsEmpty().get()) {
                   // System.out.println("enter loop test");
                    gameObject.getQueueHandlers().get(0).getLock().wait();

                }
                gameObject.getQueueHandlers().get(0).addCommand(new HelloWorldCommand());
                //System.out.println("add command test");

                gameObject.getQueueHandlers().get(0).getIsEmpty().set(true);
                gameObject.getQueueHandlers().get(0).getLock().notifyAll();

                while (gameObject.getQueueHandlers().get(0).getIsEmpty().get()) {
                    gameObject.getQueueHandlers().get(0).getLock().wait();
                }
            }

        System.out.flush();
        String s = baos.toString();
        Assertions.assertLinesMatch(
                List.of("Hello World\n".split("\n")),
                List.of(s.split("\n"))
                );
    }
}
