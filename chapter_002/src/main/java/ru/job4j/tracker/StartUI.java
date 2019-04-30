package ru.job4j.tracker;

/**
 * @version 1.0.
 * @since 22/04/2019.
 * @author Evgeniya Tsiurupa
 */
public class StartUI {

    /**
     * Получение данных от пользователя.
     */
    public final Input input;
    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструктор, инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основной цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        menu.show();
        do {
            int key = Integer.valueOf(input.ask("Введите пункт меню:"));
            menu.select(key);
            menu.show();
        } while (Integer.parseInt(input.ask("Введите пункт меню:")) != 6);
    }


    /**
     * Запуск программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
