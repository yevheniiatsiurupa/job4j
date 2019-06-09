package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 09/06/2019
 */

public class SimpleContainer<E> implements Iterable<E> {
    /**
     * Поле.
     * Динамический контейнер.
     */
    private Object[] container;

    /**
     * Поле.
     * Счетчик изменений.
     */
    private int modCount = 0;

    /**
     * Поле.
     * Емкость списка по умолчанию.
     */
    private int defaultCapacity = 10;

    /**
     * Поле.
     * Количество заполненных элементов.
     */
    private int size = 0;

    public SimpleContainer() {
        this.container = new Object[defaultCapacity];
    }

    private void grow() {
        container = Arrays.copyOf(container, (container.length * 2));
    }

    /**
     * Метод для добавления элемента в список.
     * @param value добавляемый элемент.
     */
    public void add(E value) {
        if (size >= container.length) {
            this.grow();
        }
        container[size++] = value;
        modCount++;
    }

    /**
     * Метод для получения элемента по индексу.
     * @param index индекс элемента.
     * @return возвращает элемент из массива по индексу.
     */
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new NoSuchElementException();
        }
        return (E) container[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentModCount = modCount;
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public E next() {
                if (currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (E) container[currentIndex++];
            }
        };
    }
}