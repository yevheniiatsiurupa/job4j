package ru.job4j.search;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 12/05/2019
 */

public class Task {
    /**
     * Поле хранит описание задания.
     */
    private String desc;

    /**
     * Поле хранит номер приоритета задания.
     */
    private int priority;

    public Task(String desc, int priority) {
        this.desc = desc;
        this.priority = priority;
    }

    public String getDesc() {
        return desc;
    }

    public int getPriority() {
        return priority;
    }
}
