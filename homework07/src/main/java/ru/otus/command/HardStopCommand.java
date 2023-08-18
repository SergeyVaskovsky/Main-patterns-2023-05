package ru.otus.command;

import ru.otus.Command;
import ru.otus.QueueHandler;
import ru.otus.ioc.FunctionWithObjects;
import ru.otus.ioc.IoC;

public class HardStopCommand implements Command {

    private final QueueHandler queueHandler;

    public HardStopCommand(QueueHandler handler) {
        this.queueHandler = handler;
    }

    @Override
    public void execute() {
        IoC.resolve("Scopes.Current.Set", queueHandler.toString());
        IoC.resolve("IoC.Register", "stopRule", (FunctionWithObjects) (args) -> false);
    }
}
