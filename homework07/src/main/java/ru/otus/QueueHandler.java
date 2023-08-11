package ru.otus;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class QueueHandler implements Runnable {

    private final BlockingQueue<Command> queue;
    private final Object lock = new Object();
    private final AtomicBoolean isEmpty = new AtomicBoolean(true);

    public QueueHandler(BlockingQueue<Command> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            Command command = null;

            //command = queue.poll();


                synchronized (lock) {
                    //System.out.println("enter sync queue");
                    while (!isEmpty.get()) {
                       // System.out.println("enter loop queue");
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                        isEmpty.set(false);

                        command = queue.poll();
                        try {
                            command.execute();
                        } catch (Exception exception) {
                            //ExceptionHandler.handle(exception, command).execute();
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

    public Object getLock() {
        return lock;
    }

    public AtomicBoolean getIsEmpty() {
        return isEmpty;
    }

}