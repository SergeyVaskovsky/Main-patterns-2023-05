package ru.otus.command;

import ru.otus.Command;
import ru.otus.QueueHandler;
import ru.otus.ioc.FunctionWithObjects;
import ru.otus.ioc.IoC;

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
