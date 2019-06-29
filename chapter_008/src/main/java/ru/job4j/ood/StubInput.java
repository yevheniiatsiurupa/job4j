package ru.job4j.ood;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 29/06/2019
 */

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
}
