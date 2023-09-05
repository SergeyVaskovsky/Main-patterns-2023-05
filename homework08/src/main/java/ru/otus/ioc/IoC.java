package ru.otus.ioc;

import java.util.HashMap;
import java.util.Map;

public class IoC {

    private final static ScopeStorage scopeStorage = new ScopeStorage();
    private static String scopeName = "root";

    public static <T> T resolve(String key, Object... args) {

        Scope scope = scopeStorage.getCurrentScope();
        if (scope == null) {
            Map<String, Map<String, Object[]>> scopeMaps = new HashMap<>();
            scopeMaps.put(scopeName, new HashMap<>());
            scope = new Scope(scopeMaps);
            scopeStorage.setCurrentScope(scope);
        }

        if ("IoC.Register".equals(key)) {
            return (T) scope.getDependencies(scopeName).put((String) args[0], args);
        }

        if ("Scopes.Current.Set".equals(key)) {
            scopeName = (String) args[0];
            if (scope.getDependencies(scopeName) == null) {
                scope.addScope(scopeName);
            }
            return null;
        }

        Object[] dependencyArgs = scope.getDependencies(scopeName).get(key);
        var dependencyResolver = (FunctionWithObjects) dependencyArgs[1];
        Object object = dependencyResolver.apply(args);
        return (T) object;
    }
}
