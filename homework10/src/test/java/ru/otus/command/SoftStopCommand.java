package ru.otus.command;

import ru.otus.Command;
import ru.otus.QueueHandler;

public class SoftStopCommand implements Command {

    private final QueueHandler handler;

    public SoftStopCommand(QueueHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute() {
        handler.getQueue().add(new HardStopCommand(handler));
    }
}
