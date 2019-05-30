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
        var count = tasks.size();
        for (var index = 0; index < tasks.size(); index++) {
            if (task.getPriority() < tasks.get(index).getPriority()) {
                count = index;
                break;
            }
        }
        tasks.add(count, task);
    }

    /**
     * Метод для выполнения задания первого по приоритету.
     * @return возвращает задание первое по приортиету или null.
     */
    public Task take() {
        return this.tasks.poll();
    }
}
