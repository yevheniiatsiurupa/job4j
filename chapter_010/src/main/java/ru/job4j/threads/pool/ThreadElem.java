package ru.job4j.threads.pool;

import ru.job4j.threads.queue.SimpleBlockingQueue;

/**
 * Класс представляет собой поток, который выполняет задания из
 * блокирующей очереди tasks, пока его не прервут.
 * @version 1.0.
 * @since 03/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class ThreadElem extends Thread {
    /**
     * Поле хранит ссылку на очередь заданий, из которой поток будет
     * извлекать задания.
     */
    private SimpleBlockingQueue<Runnable> tasks;

    private boolean isStopped = false;

    public ThreadElem(SimpleBlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
    }

    /**
     * Метод для запуска потока.
     * Извлекает задание из блокирующей очереди.
     * Если очередь пустая, то поток переходит в состояние ожидания.
     * (Это указано в классе блокирующей очереди).
     * Задание выполняется.
     */
    public void run() {
        while (!isStopped) {
            try {
                Runnable task = tasks.poll();
                task.run();
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void doStop() {
        isStopped = true;
    }

}
