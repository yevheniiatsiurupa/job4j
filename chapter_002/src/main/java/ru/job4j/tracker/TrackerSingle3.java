package ru.job4j.tracker;

public class TrackerSingle3 {
    /**
     * Статическая переменная для хранения единственного экземпляра.
     */
    private static final TrackerSingle3 INSTANCE = new TrackerSingle3();

    /**
     * Приватный конструктор.
     */
    private TrackerSingle3() {
    }

    /**
     * Метод getInstance() создает и возвращает экземпляр.
     * @return возращает единственный экзмепляр TrackerSingle3.
     */
    public static TrackerSingle3 getInstance() {
        return INSTANCE;
    }
}
