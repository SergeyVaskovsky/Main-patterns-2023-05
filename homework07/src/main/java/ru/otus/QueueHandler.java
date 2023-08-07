package ru.otus;

import java.util.concurrent.BlockingQueue;

public class QueueHandler implements Runnable {

    private final BlockingQueue<Command> queue;

    public QueueHandler(BlockingQueue<Command> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            Command command = queue.poll();
            try {
                command.execute();
            } catch (Exception exception) {
                //ExceptionHandler.handle(exception, command).execute();
            }
        }
    }
}