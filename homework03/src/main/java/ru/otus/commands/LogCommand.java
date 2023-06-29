package ru.otus.commands;

import ru.otus.Command;

public class LogCommand implements Command {

    @Override
    public void execute() {
        System.out.println("log command");
    }
}
