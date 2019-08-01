package ru.job4j.threads.queue;

/**
 * @version 1.0.
 * @since 01/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class ConsumerThread<T> extends Thread {
    private SimpleBlockingQueue<T> queue;

    public ConsumerThread(SimpleBlockingQueue<T> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            this.queue.poll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
