package ru.job4j.threads.nonblock;

/**
 * @version 1.0.
 * @since 02/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class Base {
    /**
     * Поле хранит уникальный ключ элемента.
     */
    private int id;

    /**
     * Поле хранит версию изменений объекта.
     */
    private int version = 0;

    public Base(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
