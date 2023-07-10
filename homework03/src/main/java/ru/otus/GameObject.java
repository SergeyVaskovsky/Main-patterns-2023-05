package ru.otus;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GameObject {
    private BlockingQueue<Command> queue = new LinkedBlockingQueue<>();
    private Command lastCommand;
    private Exception lastException;

    public BlockingQueue<Command> getQueue() {
        return queue;
    }

    public Command getLastCommand() {
        return lastCommand;
    }

    public void setLastCommand(Command lastCommand) {
        this.lastCommand = lastCommand;
    }

    public Exception getLastException() {
        return lastException;
    }

    public void setLastException(Exception lastException) {
        this.lastException = lastException;
    }
}
