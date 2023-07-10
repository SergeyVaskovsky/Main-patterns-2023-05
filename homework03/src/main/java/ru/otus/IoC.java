package ru.otus;

public class IoC {

    private static final GameObject gameObject = new GameObject();

    public static <T> T resolve(String key) {
        if ("game".equals(key)) {
            return (T) gameObject;
        } else {
            return null;
        }
    }
}
