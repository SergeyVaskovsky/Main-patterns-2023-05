package ru.otus.commands;

import ru.otus.Command;

import java.util.Map;

public class AddToQueueCommand implements Command {

    private Map<String, Object> parameters;

    public void execute() throws Exception {
        //queue.getQueue().add(logCommand);
    }

    @Override
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}
