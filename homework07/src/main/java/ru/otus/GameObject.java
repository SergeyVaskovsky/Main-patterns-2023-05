package ru.otus;

import ru.otus.ioc.FunctionWithObjects;
import ru.otus.ioc.IoC;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class GameObject {

    private final List<Thread> threads = new ArrayList<>();

    public void addNewThread() {
        IoC.resolve("IoC.Register", "queueHandler", (FunctionWithObjects) (args) -> new QueueHandler(new LinkedBlockingQueue<>()));
        var handler = (QueueHandler) IoC.resolve("queueHandler");
        var handlers = (ArrayList<QueueHandler>) IoC.resolve("queueHandlers");
        handlers.add(handler);
        Thread thread = new Thread(handler);
        threads.add(thread);
        thread.start();
    }

    public List<Thread> getThreads() {
        return threads;
    }
}
