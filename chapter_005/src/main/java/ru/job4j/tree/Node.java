package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

public class Node<E extends Comparable<E>> {
    /**
     * Список дочерних элментов для узла.
     */
    private final List<Node<E>> children = new ArrayList<>();

    /**
     * Значение, которое хранит узел.
     */
    private final E value;

    public Node(final E value) {
        this.value = value;
    }

    /**
     * Метод добавляет дочерний элемент к узлу.
     * @param child добавляемый элемент.
     */
    public void add(Node<E> child) {
        this.children.add(child);
    }

    /**
     * Метод возвращает список дочерних элементов для узла.
     * @return возвращает список элементов.
     */
    public List<Node<E>> leaves() {
        return this.children;
    }

    /**
     * Метод проверяет равенство значений value, которое хранится в узле
     * и другого значения.
     * @param that сравниваемое значение.
     * @return возвращает true, если значения равны.
     */
    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;

    }

    public E getValue() {
        return value;
    }
}
