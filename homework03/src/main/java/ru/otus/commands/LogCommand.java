package ru.otus.commands;

import ru.otus.Command;
import ru.otus.GameObject;
import ru.otus.IoC;

public class LogCommand implements Command {

    @Override
    public void execute() {
        Exception exception = ((GameObject) IoC.resolve("game")).getLastException();
        if (exception != null) {
            System.out.println(exception.getMessage());
        }
    }
}
