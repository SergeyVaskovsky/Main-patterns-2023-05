package ru.otus;

import java.util.HashMap;
import java.util.Map;


public class Scope {

    private final Map<String, Map<String, Object[]>> dependencies;

    public Scope(Map<String, Map<String, Object[]>> dependencies) {
        this.dependencies = dependencies;
    }

    public Map<String, Object[]> getDependencies(String scopeName) {
        return dependencies.get(scopeName);
    }

    public void addScope(String scopeName) {
        dependencies.put(scopeName, new HashMap<>());
    }


}
