package ru.otus.command;

import ru.otus.Command;

public class HelloWorldCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Hello World");
    }
}
