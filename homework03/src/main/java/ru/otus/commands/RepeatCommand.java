package ru.otus.commands;

import ru.otus.Command;
import ru.otus.GameObject;
import ru.otus.IoC;

import java.util.concurrent.LinkedBlockingQueue;

public class RepeatCommand implements Command {

    private int quantity;

    public RepeatCommand(int quantity) {
        this.quantity = quantity;
    }

    public void execute() throws Exception {
        LinkedBlockingQueue<Command> queue = (LinkedBlockingQueue<Command>) ((GameObject) IoC.resolve("game")).getQueue();

        if (quantity <= 0) {
            queue.add(new LogCommand());
            return;
        }

        Command command = ((GameObject) IoC.resolve("game")).getLastCommand();
        queue.add(command);
        quantity--;
    }
}
