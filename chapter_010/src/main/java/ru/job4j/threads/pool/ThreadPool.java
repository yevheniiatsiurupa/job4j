package ru.job4j.threads.pool;

import ru.job4j.threads.queue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс содержит пул тредов threads и внутреннюю блокирующую очередь tasks,
 * в которую добавляются задания для выполнения.
 * При создании пула создаются потоки, в каждый из которых
 * передается очередь tasks. Эти потоки запускаются. При запуске они пытаются
 * получить элемент из очереди tasks и пока там нет элементов переходят в режим wait.
 *
 * Задание (объект Runnable) передается в метод work.
 * Там оно добавляется в очередь заданий tasks, что дает сигнал потокам, что можно
 * взять из очереди задание и какой-то поток его выполняет.
 *
 * @version 1.0.
 * @since 03/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class ThreadPool {
    /**
     * Поле хранит пул потоков.
     */
    private final List<ThreadElem> threads = new LinkedList<>();

    /**
     * Поле хранит блокирующую очередь заданий.
     */
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(2);

    /**
     * Поле хранит информацию остановлен ли пул потоков.
     */
    private boolean isStopped = false;

    /**
     * Конструктор.
     * Создаются потоки и добавляются в список threads.
     * Количество потоков равно количеству ядер в системе (size).
     * Созданные потоки запускаются.
     */
    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            threads.add(new ThreadElem(this.tasks));
        }
        for (Thread tmp : threads) {
            tmp.start();
        }
    }

    /**
     * Метод для выполнения задания job. Передает его в очередь заданий,
     * откуда оно потом будет запущено одним из свободных потоков из threads.
     * @param job выполняемое задание.
     */
    public void work(Runnable job) throws Exception {
        if (isStopped) {
            throw new IllegalStateException("Pool was stopped.");
        }
        this.tasks.offer(job);
    }

    /**
     * Метод для остановки работы пула тредов.
     * Меняет переменную isStopped на true, чтобы больше нельзя было вызвать
     * метод work для пула.
     * Все потоки из списка threads прерываются.
     */
    public void shutdown() {
        this.isStopped = true;
        for (ThreadElem tmp : threads) {
            tmp.doStop();
        }
    }

    public static void main(String[] args) throws Exception {
        ThreadPool pool = new ThreadPool();
        Runnable taskA = new RunnableTask("task A");
        Runnable taskB = new RunnableTask("task B");
        Runnable taskC = new RunnableTask("task C");
        Runnable taskD = new RunnableTask("task D");

        pool.work(taskA);
        pool.work(taskB);
        pool.work(taskC);
        pool.work(taskD);

        pool.shutdown();
    }
}
