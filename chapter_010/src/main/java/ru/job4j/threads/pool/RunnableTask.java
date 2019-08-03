package ru.job4j.threads.pool;

/**
 * Класс задание для выполнения в пуле потоков.
 *
 * @version 1.0.
 * @since 03/08/2019.
 * @author Evgeniya Tsiurupa
 */
public class RunnableTask implements Runnable {
    /**
     * Поле хранит имя задания.
     */
    private String taskName;

    public RunnableTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        try {
            System.out.println(String.format("Start of %s in %s", this.taskName, Thread.currentThread().getName()));
            Thread.currentThread().sleep(3000);
            System.out.println(String.format("End of %s in %s", this.taskName, Thread.currentThread().getName()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
