package ru.job4j.exam.tictactoe;

import java.util.Scanner;

public class ConsoleInput {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Метод считывает ответы с консоли.
     * @param question вопрос.
     * @return ответ пользователя.
     */
    public String ask(String question) {
        System.out.println(question);
        return this.scanner.nextLine();
    }
}
