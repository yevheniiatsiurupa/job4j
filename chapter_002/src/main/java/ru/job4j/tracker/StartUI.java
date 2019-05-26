package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
     * Поле для организации вывода данных.
     */
    private final Consumer<String> output;

    private boolean working = true;
    /**
     * Конструктор, инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
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
        MenuTracker menu = new MenuTracker(this.input, this.tracker, output);
        List<Integer> range = new ArrayList<>();
        menu.fillActions();
        for (int i = 0; i < menu.getActionsLength(); i++) {
            range.add(i);
        }
        do {
            menu.show();
            int key = input.ask("Введите пункт меню:", range);
            menu.select(key);
            if (key == 6) {
                this.working = false;
            }
        } while (this.working);
    }


    /**
     * Запуск программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker(), System.out::println).init();
    }
}
