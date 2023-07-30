package ru.otus;

public class B {

    private final A a;

    public B(A a) {
        this.a = a;
    }

    void printHello() {
        System.out.println("Hello World from B");
    }
}
