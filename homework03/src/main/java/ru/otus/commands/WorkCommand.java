package ru.otus.commands;

import ru.otus.Command;
import ru.otus.exceptions.WorkException;

import java.util.Map;

public class WorkCommand implements Command {

    private Map<String, Object> parameters;

    @Override
    public void execute() throws Exception {
        System.out.println("do work");
        throw new WorkException();
    }

    @Override
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}
