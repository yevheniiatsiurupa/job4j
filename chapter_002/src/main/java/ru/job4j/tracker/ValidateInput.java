package ru.job4j.tracker;

import java.util.List;

public class ValidateInput implements Input {
    private final Input input;
    public ValidateInput(final Input input) {
        this.input = input;
    }
    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    /**
     * Метод реализует аналогичный метод из интерфейса Input.
     * @param question вопрос при вызове метода ask (с проверкой ввода чисел).
     * @param range массив допустимых значений для ввода.
     * @return возвращает введенное значение, если оно является целым числом и находится в пределах массива допустимых
     * значений (не выбрасывает исключений).
     */
    @Override
    public int ask(String question, List<Integer> range){
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter valid data again.");
            }
        } while (invalid);
        return value;

    }
}
