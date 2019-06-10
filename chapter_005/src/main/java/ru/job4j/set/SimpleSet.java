package ru.job4j.set;

import ru.job4j.list.SimpleContainer;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 10/06/2019
 */

public class SimpleSet<E> implements Iterable<E> {
    /**
     * Поле.
     * Внутренний контейнер для элементов.
     */
    private SimpleContainer<E> container = new SimpleContainer<>();

    /**
     * Метод добавляет элементы в контейнер уникальные элементы.
     * @param e добавляемый элемент.
     */
    public void add(E e) {
        E result = null;
        for (E tmp : container) {
            if (tmp.equals(e)) {
                result = tmp;
                break;
            }
        }
        if (result == null) {
            container.add(e);
        }
    }

    /**
     * Метод для получения длины списка
     */
    public int size() {
        return this.container.size();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int position = 0;
            @Override
            public boolean hasNext() {
                return position < container.size();
            }

            @Override
            public E next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return container.get(position++);
            }
        };
    }
}
