package ru.otus;

@FunctionalInterface
public interface FunctionWithObjects {
    Object apply(Object... parameters);
}
