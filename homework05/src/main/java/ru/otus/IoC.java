package ru.otus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IoC {

    private static final Map<String, Object[]> dependencies =
            new ConcurrentHashMap<>();

    public static <T> T resolve(String key, Object... args) {
        if ("IoC.Register".equals(key)) {
            return (T) dependencies.put((String)args[0], args);
        }

        if ("Scopes.Current.Set".equals(key)) {

        }

        Object[] dependencyArgs = dependencies.get(key);
        var dependencyResolver = (FunctionWithObjects) dependencyArgs[1];
        Object object = dependencyResolver.apply(args);
        return (T) object;
    }
}
