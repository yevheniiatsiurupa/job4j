package ru.job4j.tree;

import java.util.*;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 14/06/2019
 */

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Корень дерева.
     */
    private Node<E> root;

    /**
     * Количество элементов дерева.
     */
    private int size = 0;

    /**
     * Счетчик изменений.
     */
    private int modCount = 0;

    /**
     * Конструктор.
     * @param value значение, которое добавляется в корень.
     */
    public Tree(E value) {
        this.root = new Node<>(value);
    }

    /**
     * Метод для добавления узла в дерево.
     * Добавляет только уникальные элементы.
     * @param parent значение в родительском узле.
     * @param child значение в добавляемом дочернем узле.
     * @return возвращает результат - добавлено ли значение.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> foundChild = this.findBy(child);
        if (foundChild.isEmpty()) {
            Optional<Node<E>> foundParent = this.findBy(parent);
            if (foundParent.isPresent()) {
                Node<E> found = foundParent.get();
                Node<E> added = new Node<>(child);
                found.add(added);
                result = true;
                size++;
                modCount++;
            }
        }
        return result;
    }

    /**
     * Метод проверяет есть ли значение в дереве.
     * Начинает проверку с корня. Добавляет его в очередь.
     * Извлекает его из очереди.
     * Если корень не содержит искомый элемент, то в очередь добавляются его потомки.
     * Потом по очереди извлекаются элементы, если элемент не содержит значение,
     * то все его потомки добавляются в очередь.
     * Таким образом, проверка происходит по уровням (корень, все элементы 1 уровня, 2 и т.д.)
     * @param value искомое значение.
     * @return возвращает узел, в котором найдено значение.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    public boolean isBinary() {
        boolean result = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (!(el.leaves().size() <= 2)) {
                result = false;
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            /**
             * Текущее значение счетчика изменений.
             */
            private int currentMod = modCount;

            /**
             * Очередь хранит узлы для выдачи значений.
             */
            private Queue<Node<E>> queue = new LinkedList<>(Collections.singletonList(root));



            @Override
            public boolean hasNext() {
                return !(queue.isEmpty());
            }

            @Override
            public E next() {
                if (modCount != currentMod) {
                    throw new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> el = queue.poll();
                for (Node<E> tmp : el.leaves()) {
                    queue.offer(tmp);
                }
                return el.getValue();
            }
        };
    }
}
