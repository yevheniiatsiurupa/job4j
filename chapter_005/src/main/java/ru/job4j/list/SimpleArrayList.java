package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 08/06/2019
 */

public class SimpleArrayList<E> {
    /**
     * Поле хранит размер списка.
     */
    private int size;

    /**
     * Поле хранит ссылку на первый элемент списка.
     */
    private Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     * @param data добавляемые данные.
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }






//    Node temp = this.first;
//        this.first = this.first.next;
//    temp.next = null;
//        this.size--;

//    public E removeFirst() {
//        final Node<E> f = first;
//        if (f == null)
//            throw new NoSuchElementException();
//        return unlinkFirst(f);
//    }

    /**
     * Реализовать метод удаления первого элемент в списке.
     * Порядок действий: создаем ссылку temp на первый элемент,
     * если элемент равен нулю - бросаем исключение,
     * создаем ссылку на данные в первом элементе и ссылку на второй элемент,
     * обнуляем значения в первом элементе,
     * ссылку first меняем с первого элемента (обнуленного) на второй (next),
     * уменьшаем размер списка.
     * @return возвращает данные из удаленного элемента.
     */
    public E delete() {
        Node<E> temp = this.first;
        if (temp == null) {
            throw new NoSuchElementException();
        }
        E element = temp.data;
        Node<E> next = temp.next;
        temp.data = null;
        temp.next = null;
        this.first = next;
        this.size--;
        return element;
    }

    /**
     * Метод получения элемента по индексу.
     * @param index индекс получаемого элемента.
     * @return возвращает элемент по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {
        /**
         * Поле хранит данные элемента списка.
         */
        E data;

        /**
         * Поле хранит ссылку на следующий элемент списка.
         */
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }
}
