package ru.otus;

import java.util.HashMap;
import java.util.Map;

public class ExceptionHandler {

    private static final Map<Class<? extends Command>, Map<Class<? extends Exception>, Command>> exceptions = new HashMap<>();

    public static Command handle(Exception exception, Command command) {
        ((GameObject) IoC.resolve("game")).setLastException(exception);
        ((GameObject) IoC.resolve("game")).setLastCommand(command);
        return exceptions.get(command.getClass()).get(exception.getClass());
    }

    public static Map<Class<? extends Command>, Map<Class<? extends Exception>, Command>> getMap() {
        return exceptions;
    }
}
