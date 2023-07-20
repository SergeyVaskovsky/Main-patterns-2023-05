package ru.otus;

import org.junit.jupiter.api.Test;

public class FactoryTest {

    @Test
    void shouldRegisterNewDependency() {
        IoC.resolve("IoC.Register", "a", (FunctionWithObjects) (args) -> new A());
        var a = (A) IoC.resolve("a");
        a.printHello();
    }

    @Test
    void shouldRegisterNewDependencyWithParam() {
        IoC.resolve("IoC.Register", "a", (FunctionWithObjects) (args) -> new A());
        IoC.resolve("IoC.Register", "b", (FunctionWithObjects) (args) -> new B((A) args[0]));
        var a = (A) IoC.resolve("a");
        var b = (B) IoC.resolve("b", a);
        a.printHello();
        b.printHello();
    }

    @Test
    void shouldRegisterNewDependencyWithDifferentScopes() {
        IoC.resolve("Scopes.Current.Set", "1");
        IoC.resolve("IoC.Register", "a", (FunctionWithObjects) (args) -> new A());
        IoC.resolve("Scopes.Current.Set", "2");
        IoC.resolve("IoC.Register", "a", (FunctionWithObjects) (args) -> new A());

        IoC.resolve("Scopes.Current.Set", "1");
        var a = (A) IoC.resolve("a");
        a.printHello();

        IoC.resolve("Scopes.Current.Set", "2");
        a = (A) IoC.resolve("a");
        a.printHello();

    }

    @Test
    void shouldRegisterNewDependencyWithParamAndParallelExecution() {
        final A[] a = new A[1];
        new Thread(() -> {
            IoC.resolve("IoC.Register", "a", (FunctionWithObjects) (args) -> new A());
            a[0] = (A) IoC.resolve("a");
            a[0].printHello();
        }).start();

        new Thread(() -> {
            IoC.resolve("IoC.Register", "b", (FunctionWithObjects) (args) -> new B((A) args[0]));
            var b = (B) IoC.resolve("b", a[0]);
            b.printHello();
        }).start();
    }
}
