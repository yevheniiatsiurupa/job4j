package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedContainer<E> implements Iterable<E> {
    /**
     * Поле
     * Размер списка.
     */
    private int size;

    /**
     * Поле.
     * Счетчик изменений.
     */
    private int modCount = 0;

    /**
     * Поле
     * Ссылка на первый элемент списка.
     */
    private Node<E> first;

    /**
     * Поле
     * Ссылка на последний элемент списка.
     */
    private Node<E> last;

    /**
     * Метод для добавления элемента в конец списка.
     * @param value добавляемый элемент.
     */
    public void add(E value) {
        if (size == 0) {
            Node<E> added = new Node<>(this.first, value, this.last);
            this.first = added;
            this.last = added;
        } else {
            Node<E> added = new Node<>(this.last, value, null);
            this.last.next = added;
            this.last = added;
        }
        size++;
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
        Node<E> result = this.first;
        if (index > 0) {
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
        }
        return result.data;
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
                Node<E> result = first;
                for (int i = 0; i < currentIndex; i++) {
                    result = result.next;
                }
                currentIndex++;
                return result.data;
            }
        };
    }

    private static class Node<E> {
        /**
         * Поле хранит данные элемента списка.
         */
        E data;

        /**
         * Поле хранит ссылку на следующий элемент списка.
         */
        Node<E> next;

        /**
         * Поле хранит ссылку на предыдущий элемент списка.
         */
        Node<E> prev;

        public Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
}
