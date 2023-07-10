package ru.otus;

public class QueueHandler {

    private final GameObject game;
    private boolean stop = false;

    public QueueHandler() {
        this.game = IoC.resolve("game");
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void handle() throws Exception {
        while (!game.getQueue().isEmpty()) {
            Command command = game.getQueue().poll();
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
