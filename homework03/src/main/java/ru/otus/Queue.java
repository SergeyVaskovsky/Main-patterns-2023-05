package ru.otus;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Queue {
    private final BlockingQueue<Command> queue = new LinkedBlockingQueue<>();

    public BlockingQueue<Command> getQueue() {
        return queue;
    }
}
