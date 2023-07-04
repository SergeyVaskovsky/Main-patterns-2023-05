package ru.otus;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class ExceptionHandler {

    private static final Map<Class<? extends Command>, Map<Class<? extends Exception>, Class<? extends Command>>> exceptions = new HashMap<>();

    public static Command handle(Exception exception, Command command, Queue<Command> queue)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends Exception> exceptionClass = exception.getClass();
        Class<? extends Command> commandClass = command.getClass();
        Class<? extends Command> newCommandClass = exceptions.get(commandClass).get(exceptionClass);
        Class<?> clazz = Class.forName(newCommandClass.getName());
        Constructor<?> ctor = clazz.getConstructor();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("exception", exception);
        parameters.put("command", command);
        parameters.put("queue", queue);
        Object object = ctor.newInstance();
        ((Command) object).setParameters(parameters);
        return (Command) object;
    }

    public static Map<Class<? extends Command>, Map<Class<? extends Exception>, Class<? extends Command>>> getMap() {
        return exceptions;
    }
}
