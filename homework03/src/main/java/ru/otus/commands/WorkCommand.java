package ru.otus.commands;

import ru.otus.Command;

public class WorkCommand implements Command {

    @Override
    public void execute() {
        System.out.println("do work");
    }
}
