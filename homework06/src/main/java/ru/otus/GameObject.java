package ru.otus;

public interface GameObject {
    Object getProperty(String key);
    Object setProperty(String key, Object newValue);
}