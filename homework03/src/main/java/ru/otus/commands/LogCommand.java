package ru.otus.commands;

import ru.otus.Command;

import java.util.Map;

public class LogCommand implements Command {

    private Map<String, Object> parameters;

    @Override
    public void execute() throws Exception {
        Exception exception = (Exception) parameters.get("exception");
        System.out.println(exception.getMessage());
    }

    @Override
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}
