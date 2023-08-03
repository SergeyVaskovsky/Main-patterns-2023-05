package ru.otus.ioc;

import ru.otus.AdapterCompiler;
import ru.otus.AdapterGenerator;
import ru.otus.Consts;
import ru.otus.GameObject;

import java.util.HashMap;
import java.util.List;
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

        if ("IoC.Adapter".equals(key)) {
            Class<?> clazz = (Class<?>) args[0];
            var adapterGenerator = new AdapterGenerator(clazz);
            var adapterCompiler = new AdapterCompiler();
            List<String> sourceCode = adapterGenerator.generate();
            return (T) adapterCompiler.compile(clazz.getSimpleName() + "Adapter", sourceCode, (GameObject) args[1], GameObject.class);

        }

        if ("IoC.Register".equals(key)) {
            return (T) scope.getDependencies(scopeName).put((String)args[0], args);
        }

        if ("Scopes.Current.Set".equals(key)) {
            scopeName = (String)args[0];
            if (scope.getDependencies(scopeName) == null) {
                scope.addScope(scopeName);
            }
            return null;
        }

        if ("Movable.position.get".equals(key)) {
            return (T) ((GameObject) args[0]).getProperty(Consts.POSITION);
        }

        if ("Movable.velocity.get".equals(key)) {
            return (T) ((GameObject) args[0]).getProperty(Consts.VELOCITY);
        }

        if ("Movable.position.set".equals(key)) {
            return (T) ((GameObject) args[0]).setProperty(Consts.POSITION, args[1]);
        }

        Object[] dependencyArgs = scope.getDependencies(scopeName).get(key);
        var dependencyResolver = (FunctionWithObjects) dependencyArgs[1];
        Object object = dependencyResolver.apply(args);
        return (T) object;
    }
}
