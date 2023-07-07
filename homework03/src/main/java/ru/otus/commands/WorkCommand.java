package ru.otus.commands;

import ru.otus.Command;
import ru.otus.exceptions.WorkException;

public class WorkCommand implements Command {

    @Override
    public void execute() throws Exception {
        System.out.println("do work");
        throw new WorkException();
    }
}
