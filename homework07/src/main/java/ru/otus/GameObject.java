package ru.otus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class GameObject {
    private final List<QueueHandler> queueHandlers = new ArrayList<>();
    private final List<Thread> threads = new ArrayList<>();

    public void addNewThread() {
        QueueHandler queueHandler = new QueueHandler(new LinkedBlockingQueue<>());
        queueHandlers.add(queueHandler);
        Thread thread = new Thread(queueHandler);
        threads.add(thread);
        thread.start();
    }

    public List<QueueHandler> getQueueHandlers() {
        return queueHandlers;
    }

    public List<Thread> getThreads() {
        return threads;
    }
}
