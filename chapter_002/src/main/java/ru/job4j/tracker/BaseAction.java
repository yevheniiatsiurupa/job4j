package ru.job4j.tracker;

public abstract class BaseAction implements UserAction {
    /**
     * Поле хранит ключ операции.
     */
    private final int key;
    /**
     * Поле хранит имя операции.
     */
    private final String name;

    /**
     * Конструктор.
     * @param key ключ операции.
     * @param name имя операции.
     */
    protected BaseAction(final int key, final String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Метод реализует аналогичный метод из интерфейса UserAction.
     * @return возвращает ключ операции.
     */
    @Override
    public int key() {
        return this.key;
    }

    /**
     * Метод реализует аналогичный метод из интерфейса UserAction.
     * @return возвращает имя операции в формате "Ключ. Имя".
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }
}
