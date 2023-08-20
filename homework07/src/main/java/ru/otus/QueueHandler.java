package ru.otus;

import ru.otus.ioc.ExceptionHandler;
import ru.otus.ioc.FunctionWithObjects;
import ru.otus.ioc.IoC;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class QueueHandler implements Runnable {

    private final BlockingQueue<Command> queue;

    public QueueHandler(BlockingQueue<Command> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        IoC.resolve("Scopes.Current.Set", this.toString());
        IoC.resolve("IoC.Register", "stopRule", (FunctionWithObjects) (args) -> true);
        Boolean run = IoC.resolve("stopRule");
        while (run) {

            Command command = queue.poll();
            if (command == null) {
                continue;
            }
            try {
                command.execute();
            } catch (Exception exception) {
                ExceptionHandler.handle(exception, command).execute();
            }
            IoC.resolve("Scopes.Current.Set", this.toString());
            run = IoC.resolve("stopRule");

        }
    }

    public BlockingQueue<Command> getQueue() {
        return queue;
    }

    public void addCommand(Command command) {
        queue.add(command);
    }
}