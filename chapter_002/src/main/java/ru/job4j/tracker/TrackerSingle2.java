package ru.job4j.tracker;

public class TrackerSingle2 {
    /**
     * Статическая переменная для хранения единственного экземпляра.
     */
    private static TrackerSingle2 instance;

    /**
     * Приватный конструктор.
     */
    private TrackerSingle2() {
    }

    /**
     * Метод getInstance() создает и возвращает экземпляр.
     * @return возращает единственный экзмепляр TrackerSingle2.
     */
    public static TrackerSingle2 getInstance() {
        if (instance == null) {
            instance = new TrackerSingle2();
        }
        return instance;
    }
}
