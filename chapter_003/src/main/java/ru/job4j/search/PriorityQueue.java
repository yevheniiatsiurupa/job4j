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
        if(tasks.size() == 0) {
            tasks.add(task);
        }
        for(Task tmp : tasks) {
            if (task.getPriority() < tmp.getPriority()) {
                tasks.add(tasks.indexOf(tmp), task);
                break;
            }
        }
    }

    /**
     * Метод для выполнения задания первого по приоритету.
     * @return возвращает задание первое по приортиету или null.
     */
    public Task take() {
        return this.tasks.poll();
    }
}
