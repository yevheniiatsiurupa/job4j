package ru.job4j.tracker;

import java.util.List;

public class StubInput implements Input {
    /**
     * Поле содержит последовательность ответов пользователя.
     */
    private final String[] value;
    /**
     * Поле считает количество вызовов метода ask.
     */
    private int position;

    /**
     * Конструктор.
     * @param value ввести последовательность ответов пользователя.
     */
    public StubInput(final String[] value) {
        this.value = value;
    }

    /**
     * Метод реализует аналогичный метод из интерфейса Input.
     * @param question вопрос при вызове метода ask.
     * @return возвращает по порядку элементы массива value с заранее определенными ответами.
     * Счетчик позиции увеличивается на 1.
     */
    @Override
    public String ask(String question) {
        return this.value[this.position++];
    }

    /**
     * Метод реализует аналогичный метод из интерфейса Input.
     * @param question вопрос при вызове метода ask (с проверкой ввода чисел).
     * @param range массив допустимых значений для ввода.
     * @return возвращает по порядку элементы массива value с заранее определенными ответами.
     * Счетчик позиции увеличивается на 1.
     */
    @Override
    public int ask(String question, List<Integer> range) {
        position++;
        int key = Integer.valueOf(this.value[this.position - 1]);
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
