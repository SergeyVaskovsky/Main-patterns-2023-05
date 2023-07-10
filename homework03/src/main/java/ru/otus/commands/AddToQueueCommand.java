package ru.otus.commands;

import ru.otus.Command;
import ru.otus.GameObject;
import ru.otus.IoC;

import java.util.concurrent.LinkedBlockingQueue;

public class AddToQueueCommand implements Command {

    public void execute() throws Exception {
        LinkedBlockingQueue<Command> queue = (LinkedBlockingQueue<Command>) ((GameObject) IoC.resolve("game")).getQueue();
        queue.add(new LogCommand());
    }
}
