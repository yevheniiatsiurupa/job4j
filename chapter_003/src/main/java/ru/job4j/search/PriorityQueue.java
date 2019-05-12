package ru.job4j.search;

import java.util.LinkedList;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 12/05/2019
 */

public class PriorityQueue {
    /**
     * Поле хранит список заданий.
     */
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод для вставки задания в список по номеру приоритета.
     * @param task задание.
     */
    public void put(Task task) {
        tasks.add(task.getPriority(), task);
    }

    /**
     * Метод для выполнения задания первого по приоритету.
     * @return возвращает задание первое по приортиету или null.
     */
    public Task take() {
        return this.tasks.poll();
    }
}
