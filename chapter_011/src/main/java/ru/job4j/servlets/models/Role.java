package ru.job4j.servlets.models;

/**
 * @version 1.0.
 * @since 14/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class Role {
    /**
     * Поле хранит название роли.
     */
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
