package ru.job4j.threads.queue;

/**
 * @version 1.0.
 * @since 01/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class ProducerThread<T> extends Thread  {
    private SimpleBlockingQueue<T> queue;
    private T element;

    public ProducerThread(SimpleBlockingQueue<T> queue, T element) {
        this.queue = queue;
        this.element = element;
    }

    public void run() {
        try {
            this.queue.offer(this.element);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
