package ru.otus;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.commands.LogCommand;
import ru.otus.commands.WorkCommand;
import ru.otus.exceptions.WorkException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExceptionsTest {

    private Queue queue;
    private QueueHandler handler;
    private ByteArrayOutputStream baos;

    @BeforeEach
    void init() {
        queue = new Queue();
        handler = new QueueHandler(queue);
        baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
    }

    @Test
    void test() throws Exception {
        WorkCommand workCommand = new WorkCommand();
        Map<Class<? extends Exception>, Class<? extends Command>> exceptionMap = new HashMap<>();
        exceptionMap.put(WorkException.class, LogCommand.class);
        ExceptionHandler.getMap().put(WorkCommand.class, exceptionMap);
        queue.getQueue().add(workCommand);
        handler.handle();

        System.out.flush();
        String s = baos.toString();
        Assertions.assertLinesMatch(
                List.of(s.split("\n")),
                List.of("do work\nworkException\n".split("\n"))
        );
    }

    @AfterEach
    void tearDown() {
        handler = null;
        queue = null;
    }
}
