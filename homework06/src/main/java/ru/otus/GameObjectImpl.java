package ru.otus;

import java.util.HashMap;
import java.util.Map;

public class GameObjectImpl implements GameObject {

    Map<String, Object> properties = new HashMap<>();

    @Override
    public Object getProperty(String key) {
        return properties.get(key);
    }

    @Override
    public Object setProperty(String key, Object newValue) {
        properties.put(key, newValue);
        return newValue;
    }
}
