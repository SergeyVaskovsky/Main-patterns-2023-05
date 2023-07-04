package ru.otus;

public class QueueHandler {

    private final Queue queue;
    private boolean stop = false;

    public QueueHandler(Queue queue) {
        this.queue = queue;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void handle() throws Exception {
        while (!queue.getQueue().isEmpty()) {
            Command command = queue.getQueue().poll();
            if (command == null) {
                continue;
            }
            try {
                command.execute();
            } catch (Exception exception) {
                ExceptionHandler.handle(exception, command, queue.getQueue()).execute();
            }
        }
    }
}
