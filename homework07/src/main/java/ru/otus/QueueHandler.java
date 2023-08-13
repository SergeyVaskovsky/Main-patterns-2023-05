package ru.otus;

import ru.otus.ioc.ExceptionHandler;

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
        while (true) {
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