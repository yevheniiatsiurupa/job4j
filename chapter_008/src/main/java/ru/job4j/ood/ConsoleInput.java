package ru.job4j.ood;

import java.util.*;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 29/06/2019
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
}

