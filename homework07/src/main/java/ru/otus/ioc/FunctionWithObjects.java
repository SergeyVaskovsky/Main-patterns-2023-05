package ru.otus.ioc;

@FunctionalInterface
public interface FunctionWithObjects {
    Object apply(Object... parameters);
}
