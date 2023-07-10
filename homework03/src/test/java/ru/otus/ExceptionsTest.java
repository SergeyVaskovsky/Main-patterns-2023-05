package ru.otus;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.commands.AddToQueueCommand;
import ru.otus.commands.LogCommand;
import ru.otus.commands.RepeatCommand;
import ru.otus.commands.WorkCommand;
import ru.otus.exceptions.WorkException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExceptionsTest {

    private GameObject gameObject;
    private ByteArrayOutputStream baos;
    private QueueHandler handler;

    @BeforeEach
    void init() {
        gameObject = IoC.resolve("game");
        handler = new QueueHandler();
        baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
    }

    /**
     * 4
     */
    @Test
    void testCommandWriteLog() {
        LogCommand logCommand = new LogCommand();
        gameObject.setLastException(new WorkException());
        logCommand.execute();
        System.out.flush();
        String s = baos.toString();
        Assertions.assertLinesMatch(
                List.of(s.split("\n")),
                List.of("workException\n".split("\n")));
    }

    /**
     * 5
     */
    @Test
    void testCommandWriteLogToQueue() throws Exception {
        WorkCommand workCommand = new WorkCommand();
        Map<Class<? extends Exception>, Command> exceptionMap = new HashMap<>();
        exceptionMap.put(WorkException.class, new AddToQueueCommand());
        ExceptionHandler.getMap().put(WorkCommand.class, exceptionMap);
        gameObject.getQueue().add(workCommand);
        handler.handle();

        System.out.flush();
        String s = baos.toString();
        Assertions.assertLinesMatch(
                List.of(s.split("\n")),
                List.of("do work\nworkException\n".split("\n"))
        );
    }

    /**
     * 6-8
     */
    @Test
    void testRepeatCommandExceptionalCommandToQueue() throws Exception {
        WorkCommand workCommand = new WorkCommand();

        Map<Class<? extends Exception>, Command> exceptionMap = new HashMap<>();
        exceptionMap.put(WorkException.class, new RepeatCommand(1));
        ExceptionHandler.getMap().put(WorkCommand.class, exceptionMap);

        gameObject.getQueue().add(workCommand);
        handler.handle();

        System.out.flush();
        String s = baos.toString();
        Assertions.assertLinesMatch(
                List.of(s.split("\n")),
                List.of("do work\ndo work\nworkException\n".split("\n"))
        );
    }

    /**
     * 9
     */
    @Test
    void testRepeatCommandTwoTimeExceptionalCommandToQueue() throws Exception {
        WorkCommand workCommand = new WorkCommand();

        Map<Class<? extends Exception>, Command> exceptionMap = new HashMap<>();
        exceptionMap.put(WorkException.class, new RepeatCommand(2));
        ExceptionHandler.getMap().put(WorkCommand.class, exceptionMap);

        gameObject.getQueue().add(workCommand);
        handler.handle();

        System.out.flush();
        String s = baos.toString();
        Assertions.assertLinesMatch(
                List.of(s.split("\n")),
                List.of("do work\ndo work\ndo work\nworkException\n".split("\n"))
        );
    }

    @AfterEach
    void tearDown() {
        gameObject = null;
        handler = null;
    }
}
