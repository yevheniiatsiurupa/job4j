package ru.job4j.tracker;

public interface UserAction {
    /**
     * Метод возвращает ключ опции.
     * @return ключ.
     */
    int key();

    /**
     * Основной метод.
     * @param input объект типа Input.
     * @param iTracker объект типа Tracker.
     */
    void execute(Input input, ITracker iTracker);

    /**
     * Метод возвращает информацию о данном пункте меню.
     * @return Строка меню.
     */
    String info();
}
