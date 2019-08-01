package ru.job4j.threads.queue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @version 1.0.
 * @since 01/08/2019.
 * @author Evgeniya Tsiurupa
 */

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int maxSize;


    public SimpleBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
    }


    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (this.queue.size() == this.maxSize) {
                System.out.println("Waiting for consumer.");
                this.wait();
            }
            this.notify();
            this.queue.offer(value);
            System.out.println("Added element: " + value);
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            while (this.queue.isEmpty()) {
                System.out.println("Waiting for producer.");
                this.wait();
            }
            this.notify();
            T polled = this.queue.poll();
            System.out.println("Polled element: " + polled);
            return polled;
        }
    }
}
