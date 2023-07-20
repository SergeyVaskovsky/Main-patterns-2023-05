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
}
