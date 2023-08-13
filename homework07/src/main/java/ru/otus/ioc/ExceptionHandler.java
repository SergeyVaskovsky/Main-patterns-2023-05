package ru.otus.ioc;

import ru.otus.Command;
import ru.otus.command.NullPointerExceptionCommand;

import java.util.HashMap;
import java.util.Map;

public class ExceptionHandler {

    private static final Map<Class<? extends Command>, Map<Class<? extends Exception>, Command>> exceptions = new HashMap<>();

    public static Command handle(Exception exception, Command command) {
        if (command == null) {
            return new NullPointerExceptionCommand();
        }
        return exceptions.get(command.getClass()).get(exception.getClass());
    }

    public static Map<Class<? extends Command>, Map<Class<? extends Exception>, Command>> getMap() {
        return exceptions;
    }
}