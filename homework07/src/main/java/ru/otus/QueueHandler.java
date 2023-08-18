package ru.otus;

import ru.otus.ioc.ExceptionHandler;
import ru.otus.ioc.FunctionWithObjects;
import ru.otus.ioc.IoC;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class QueueHandler implements Runnable {

    private final BlockingQueue<Command> queue;
    private final AtomicBoolean lock = new AtomicBoolean(true);

    public QueueHandler(BlockingQueue<Command> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        IoC.resolve("Scopes.Current.Set", this.toString());
        IoC.resolve("IoC.Register", "stopRule", (FunctionWithObjects) (args) -> true);
        Boolean run = (Boolean) IoC.resolve("stopRule");
        while (run) {
            Command command;

            synchronized (lock) {
                while (!lock.get()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                lock.set(false);

                command = queue.poll();
                try {
                    command.execute();
                } catch (Exception exception) {
                    ExceptionHandler.handle(exception, command).execute();
                }
                lock.notifyAll();

            }
            IoC.resolve("Scopes.Current.Set", this.toString());
            run = (Boolean) IoC.resolve("stopRule");

        }
    }

    public BlockingQueue<Command> getQueue() {
        return queue;
    }

    public void addCommand(Command command) {
        queue.add(command);
    }

    public AtomicBoolean getLock() {
        return lock;
    }

}