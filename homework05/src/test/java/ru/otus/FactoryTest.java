package ru.otus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class FactoryTest {

    private ByteArrayOutputStream baos;

    @BeforeEach
    void init() {
        baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
    }

    @Test
    void shouldRegisterNewDependency() {
        IoC.resolve("IoC.Register", "a", (FunctionWithObjects) (args) -> new A());
        var a = (A) IoC.resolve("a");
        a.printHello();

        System.out.flush();
        String s = baos.toString();
        Assertions.assertLinesMatch(
                List.of(s.split("\n")),
                List.of("Hello World from A\n".split("\n"))
        );
    }

    @Test
    void shouldRegisterNewDependencyWithParam() {
        IoC.resolve("IoC.Register", "a", (FunctionWithObjects) (args) -> new A());
        IoC.resolve("IoC.Register", "b", (FunctionWithObjects) (args) -> new B((A) args[0]));
        var a = (A) IoC.resolve("a");
        var b = (B) IoC.resolve("b", a);
        a.printHello();
        b.printHello();

        System.out.flush();
        String s = baos.toString();
        Assertions.assertLinesMatch(
                List.of(s.split("\n")),
                List.of("Hello World from A\nHello World from B\n".split("\n"))
        );
    }

    @Test
    void shouldRegisterNewDependencyWithDifferentScopes() {
        IoC.resolve("IoC.Register", "b", (FunctionWithObjects) (args) -> new B(new A()));
        IoC.resolve("Scopes.Current.Set", "1");
        IoC.resolve("IoC.Register", "a", (FunctionWithObjects) (args) -> new A());
        IoC.resolve("Scopes.Current.Set", "2");
        IoC.resolve("IoC.Register", "a", (FunctionWithObjects) (args) -> new CA());

        IoC.resolve("Scopes.Current.Set", "1");
        var a = (A) IoC.resolve("a");
        a.printHello();

        IoC.resolve("Scopes.Current.Set", "2");
        a = (A) IoC.resolve("a");
        a.printHello();

        IoC.resolve("Scopes.Current.Set", "root");
        var b = (B) IoC.resolve("b");
        b.printHello();

        System.out.flush();
        String s = baos.toString();
        Assertions.assertLinesMatch(
                List.of(s.split("\n")),
                List.of("Hello World from A\nHello World from CA\nHello World from B\n".split("\n"))
        );
    }

    @Test
    void shouldRegisterNewDependencyWithParamAndParallelExecution() throws InterruptedException {
        final A[] a = new A[1];
        var t1 = new Thread(() -> {
            IoC.resolve("IoC.Register", "a", (FunctionWithObjects) (args) -> new A());
            a[0] = (A) IoC.resolve("a");
            a[0].printHello();
        });
        t1.start();


        final B[] b = new B[1];
        var t2 = new Thread(() -> {
            IoC.resolve("IoC.Register", "b", (FunctionWithObjects) (args) -> new B((A) args[0]));
            b[0] = (B) IoC.resolve("b", a[0]);
            b[0].printHello();
        });
        t2.start();

        t1.join();
        t2.join();

        System.out.flush();
        String s = baos.toString();
        Assertions.assertLinesMatch(
                List.of(s.split("\n")),
                List.of("Hello World from A\nHello World from B\n".split("\n"))
        );
    }
}
