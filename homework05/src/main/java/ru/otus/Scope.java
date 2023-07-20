package ru.otus;

import java.util.Map;
import java.util.function.Function;

public class Scope implements ScopeInterface {

    private final Map<String, FunctionWithObjects> dependencies;
    private final ScopeInterface parent;

    public Scope(Map<String, FunctionWithObjects> dependencies, ScopeInterface parent) {
        this.dependencies = dependencies;
        this.parent = parent;
    }

    @Override
    public Object resolve(String key, Object... args) {
        var dependencyResolver = dependencies.get(key);
        if (dependencyResolver != null) {
            return dependencyResolver.apply(args);
        } else {
            return parent.resolve(key, args);
        }
    }
}
