package ru.otus;

import java.util.concurrent.BlockingQueue;

public class QueueHandler {

    private BlockingQueue<Command> blockingQueue;
    private boolean stop = false;

    public void handle() {
        while (!stop) {

            Command command = blockingQueue.poll();
            if (command == null) {
                continue;
            }

            try {
                command.execute();
            } catch (Exception exception) {
                ExceptionHandler.handle(exception, command).execute();
            }
        }
    }
}
