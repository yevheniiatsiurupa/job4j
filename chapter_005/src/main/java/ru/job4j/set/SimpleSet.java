package ru.job4j.set;

import ru.job4j.list.SimpleContainer;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeSet;

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
     * Метод проверяет наличие элемента в коллекции.
     * @param e проверяемый элемент.
     * @return возвращает true, если элемент содержится в коллекции.
     */
    public boolean contains(E e) {
        boolean result = false;
        for (E tmp : container) {
            if (e == null) {
                if (tmp == null) {
                    result = true;
                    break;
                }
            } else {
                if (tmp != null && tmp.equals(e)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Метод добавляет элементы в контейнер уникальные элементы.
     * @param e добавляемый элемент.
     */
    public void add(E e) {
        if (!this.contains(e)) {
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
