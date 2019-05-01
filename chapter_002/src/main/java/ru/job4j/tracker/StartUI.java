package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0.
 * @since 22/04/2019.
 * @author Evgeniya Tsiurupa
 * Класс для запуска программы.
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
     * Создаем объект MenuTracker.
     * Заполняем массив меню.
     * Вывод меню на экран.
     * Заполняем массив range с допустимыми значениями ввода.
     * Вызов метода select для объекта menu (для выполнения пунтка меню) и
     * последующий показ меню, пока пользователь не введет пункт меню 6 - выход.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        List<Integer> range = new ArrayList<>();
        menu.fillActions();
        menu.show();
        for (int i = 0; i < menu.getActionsLength(); i++) {
            range.add(i);
        }
        do {
            menu.select(input.ask("Введите пункт меню:", range));
            menu.show();
        } while (Integer.parseInt(input.ask("Введите пункт меню:")) != 6);
    }


    /**
     * Запуск программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}
