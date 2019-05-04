package ru.job4j.tracker;

public class TrackerSingle4 {
    /**
     *Приватный конструктор.
     */
    private TrackerSingle4() {
    }

    /**
     * Метод возвращает поле внутреннего класса Holder,
     * @return возвращает поле внутреннего класса Holder.
     */
    public static TrackerSingle4 getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Внутренний класс, в котором находится единственный объект.
     */
    private static final class Holder {
        private static final TrackerSingle4 INSTANCE = new TrackerSingle4();
    }
}
