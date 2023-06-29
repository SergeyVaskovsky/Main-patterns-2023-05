package ru.otus;

import java.util.HashMap;
import java.util.Map;

public class ExceptionHandler {

    private static final Map<Class<Command>, Map<Class<Exception>, Command>> exceptions = new HashMap<>();

    public static Command handle(Exception exception, Command command) {
        Class<? extends Exception> exceptionClass = exception.getClass();
        Class<? extends Command> commandClass = command.getClass();

        return exceptions.get(commandClass).get(exceptionClass);
    }
}
