package ru.job4j.list;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 09/06/2019
 */

public class SimpleQueue<T> {
    private SimpleStack<T> inStack = new SimpleStack<>();
    private SimpleStack<T> outStack = new SimpleStack<>();


    /**
     * Метод возвращает значение из коллекции и удаляет его.
     * Если коллекция пустая - возвращает null.
     * При вызове метода элементы inStack перезаписываются в outStack.
     * Таким образом первый добавленный элемент в inStack становится последним добавленным в outStack.
     * Этот же элемент будет первым в очереди при вызове poll.
     */
    public T poll() {
        T result = null;
        if (inStack.size() != 0 || outStack.size() != 0) {
            while (inStack.size() != 0) {
                outStack.push(inStack.poll());
            }
            result = outStack.poll();
        }
        return result;
    }

    /**
     * Метод помещает значение в коллекцию inStack.
     */
    public void push(T value) {
        inStack.push(value);
    }
}
