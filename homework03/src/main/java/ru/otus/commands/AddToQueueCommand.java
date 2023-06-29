package ru.otus.commands;

import ru.otus.Command;

import java.util.concurrent.BlockingQueue;

public class AddToQueueCommand implements Command {

    private final BlockingQueue<Command> queue;
    private final LogCommand logCommand;

    public AddToQueueCommand(BlockingQueue<Command> queue, LogCommand logCommand) {
        this.queue = queue;
        this.logCommand = logCommand;
    }

    public void execute() {
        queue.add(logCommand);
    }
}
