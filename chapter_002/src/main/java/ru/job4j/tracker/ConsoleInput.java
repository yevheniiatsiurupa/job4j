package ru.job4j.tracker;

import java.util.*;

/**
 * @version 1.0.
 * @since 22/04/2019.
 * @author Evgeniya Tsiurupa.
 * Класс для ввода с консоли.
 * Реализует интерфейс Input.
 */
public class ConsoleInput implements Input {
    /**
     * Создание объекта scanner, который считывает данные вводимые из консоли (System.in)
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Метод возвращает данные введенные в консоли после заданного вопроса.
     * @param question вопрос, который выводится на экран пользователю.
     * @return возвращает строку с вводом пользователя.
     */
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * Метод возвращает данные введенные в консоли после заданного вопроса.
     * Применяется в случае, если пользователь должен ввести определенный пункт меню.
     * @param question вопрос, который выводится на экран пользователю.
     * @param range массив допустимых для ввода значений.
     * @throws MenuOutException исключение при вводе данных, которые не входят в массив range.
     * @return возвращает число, которое ввел пользователь.
     */
    @Override
    public int ask(String question, List<Integer> range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Out of Menu range");
        }
        return key;
    }
}
