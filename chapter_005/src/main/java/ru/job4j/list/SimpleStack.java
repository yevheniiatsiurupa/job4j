package ru.job4j.list;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 09/06/2019
 */

public class SimpleStack<E> {
    /**
     * Поле.
     * Внутренний односвязный список.
     */
    private SimpleArrayList<E> stack = new SimpleArrayList<>();

    /**
     * Метод возвращает значение из коллекции и удаляет его.
     */
    public E poll() {
        return stack.delete();
    }

    /**
     * Метод помещает значение в коллекцию.
     */
    public void push(E value) {
        stack.add(value);
    }

    /**
     * Метод определяет размер стека.
     */
    public int size() {
        return stack.getSize();
    }
}
