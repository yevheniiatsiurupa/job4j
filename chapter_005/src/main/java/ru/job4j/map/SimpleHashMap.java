package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 12/06/2019
 */


public class SimpleHashMap<K, V> {
    /**
     * Внутренний массив, хранящий элементы Node.
     */
    private Node<K, V>[] table;

    /**
     * Начальная емкость внутреннего массива.
     */
    private static final int INITIAL_CAPACITY = 4;

    private int capacity;

    /**
     * Количество добавленных элементов.
     */
    private int size = 0;

    /**
     * Количество заполненных корзин.
     */
    private int threshold = 0;

    /**
     * Счетчик изменений.
     */
    private int modCount = 0;

    /**
     * Метод для вычисления хешкода.
     * @param h входящий хешкод.
     * @return возвращает новый хешкод.
     */
    public int hash(int h) {
        return h ^ (h >>> 16);
    }

    /**
     * Метод для вычисления индекса во внутреннем массиве по хешкоду элемента.
     * @param hash хешкод элемента.
     * @param length длина массива.
     * @return возвращает индекс массива по хешкоду.
     */
    public int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    /**
     * Метод для вычисления индекса массива по ключу.
     * @param key ключ.
     * @param length длина массива.
     * @return возвращает индекс массива.
     */
    public int indexByKey(K key, int length) {
        int result;
        if (key == null) {
            result = 0;
        } else {
            int h = key.hashCode();
            int hashResult = this.hash(h);
            result = this.indexFor(hashResult, length);
        }

        return result;
    }

    /**
     * Метод проверяет есть ли в связанном списке узел с искомым хешкодом.
     * @param first ссылка на первый узел связанного списка.
     * @param hash искомый хешкод.
     * @return возвращает результат (найден ли узел).
     */
    public boolean containsHashAndKey(Node<K, V> first, K key) {
        boolean result = false;
        Node<K, V> temp = first;
        do {
            if (temp.hash == key.hashCode() && temp.key.equals(key)) {
                    result = true;
                    break;
            }
            temp = temp.next;
        }
        while (temp != null);
        return result;
    }

    /**
     * Метод увеличивает внутренний массив.
     * Создается новый массив, куда перезаписываются все элементы старого массива.
     */
    public void resize() {
        Node<K, V>[] newtab;
        if (size == 0) {
            newtab = (Node<K, V>[]) new Node[INITIAL_CAPACITY];
        } else {
            int newCapacity = table.length * 2;
            newtab = (Node<K, V>[]) new Node[newCapacity];
            int newThreshold = 0;
            for (Node<K, V> tmp : table) {
                if (tmp != null) {
                    do {
                        int index = this.indexByKey(tmp.key, newCapacity);
                        if (newtab[index] == null) {
                            newtab[index] = new Node<>(tmp.key.hashCode(), tmp.key, tmp.value, null);
                            newThreshold++;
                        } else {
                            Node<K, V> added = new Node<>(tmp.key.hashCode(), tmp.key, tmp.value, newtab[index]);
                            newtab[index] = added;
                        }
                    }
                    while (tmp.next != null);
                }
            }
            threshold = newThreshold;
        }
        table = newtab;
    }

    /**
     * Метод для вставки элемента с ключом null.
     * @param key ключ.
     * @param value значение.
     * @return возвращает результат, выполнилась ли операция.
     */
    private boolean insertNull(K key, V value) {
        boolean result = false;
        if (table[0] == null) {
            table[0] = new Node<>(0, key, value, null);
            result = true;
            size++;
            threshold++;
            modCount++;
        }
        return result;
    }
    /**
     * Метод для добавления элемента в карту по ключу.
     * @param key ключ.
     * @param value значение.
     * @return возвращает результат выполнения операции.
     */
    public boolean insert(K key, V value) {
        if (size == 0) {
            this.resize();
        }
        if (key == null) {
            return this.insertNull(key, value);
        }
        boolean result = false;
        if (threshold == table.length) {
            this.resize();
        }
        int index = this.indexByKey(key, table.length);
        if (table[index] == null) {
            table[index] = new Node<>(key.hashCode(), key, value, null);
            threshold++;
            result = true;
        } else {
            if (!this.containsHashAndKey(table[index], key)) {
                Node<K, V> added = new Node<>(key.hashCode(), key, value, table[index]);
                table[index] = added;
                result = true;
            }
        }
        size++;
        modCount++;
        return result;
    }

    /**
     * Метод показывает количество добавленных значений.
     * @return возвращает количество добавленных значений.
     */
    public int size() {
        return this.size;
    }

    /**
     * Метод для получения значения по ключу.
     * @param key ключ.
     * @return возвращает значение.
     */
    public V get(K key) {
        if (key == null) {
            return table[0].value;
        }
        int index = this.indexByKey(key, table.length);
        Node<K, V> first = table[index];
        V result = null;
        do {
            if (first.key.equals(key)) {
                result = first.value;
                break;
            }
            first = first.next;
        } while (first != null);
        return result;
    }

    /**
     * Метод удаляет элемент с ключом null.
     * @return возвращает удалось ли провести операцию.
     */
    private V deleteNullKey() {
        V result = null;
        if (table[0] != null) {
            result = table[0].value;
            table[0] = null;
            size--;
            threshold--;
            modCount++;
        }
        return result;
    }

    /**
     * Метод удаляет элемент по ключу.
     * Если ключ равен нулю, то обнуляется нулевая ячейка массива, если она содержит узел.
     * Если ключ не равен нулю, тогда находится нужная корзина.
     * Если корзина пустая, вернуть false.
     * Если
     * @param key искомый ключ.
     * @return вернуть результат операции.
     */
    public V delete(K key) {
        if (key == null) {
            return this.deleteNullKey();
        }
        int index = this.indexByKey(key, table.length);
        Node<K, V> tmp = table[index];
        Node<K, V> prev = null;
        if (tmp == null) {
            return null;
        }
        boolean found = false;
        do {
            if (tmp.key.equals(key)) {
                found = true;
                break;
            }
            prev = tmp;
            tmp = tmp.next;
        } while (tmp != null);
        V result = null;
        if (found) {
            result = tmp.value;
            Node<K, V> next = tmp.next;
            tmp.value = null;
            tmp.key = null;
            tmp.next = null;
            if (prev == null) {
                table[index] = next;
            } else {
                prev.next = next;
            }
            size--;
            threshold--;
            modCount++;
        }
        return result;
    }


    /**
     * Абстрактный класс итератора.
     */
    abstract class HashIterator {
        private int currentModCount;    // for fast-fail
        private int currentIndex;       // current slot
        private Node<K, V> current;     // current entry
        private Node<K, V> next;        // next entry to return

        /**
         * Конструктор.
         * Устанавливает начальные значения полей.
         * Устанавливает значение next на первую непустую корзину.
         */
        public HashIterator() {
            this.currentModCount = modCount;
            this.currentIndex = 0;
            this.current = null;
            this.next = null;
            Node<K, V>[] t = table;
            if (t != null && size > 0) { // advance to first entry
                do {
                    next = t[currentIndex++];
                } while (currentIndex < t.length && next  == null);
            }
        }

        public boolean hasNext() {
            return next != null;
        }

        /**
         * Метод возвращает следующий узел.
         */
        public Node<K, V> nextNode() {
            Node<K, V>[] t;
            Node<K, V> e = next;
            if (modCount != currentModCount) {
                throw new ConcurrentModificationException();
            }
            if (e == null) {
                throw new NoSuchElementException();
            }
            current = e;
            next = current.next;
            t = table;
            if (next == null && t != null) {
                do {
                    next = t[currentIndex++];
                } while (currentIndex < t.length && next == null);
            }
            return e;
        }
    }


    final class KeyIterator extends HashIterator
            implements Iterator<K> {
        /**
         * Метод возвращает следующий ключ.
         */
        public final K next() {
            return nextNode().key;
        }
    }

    final class ValueIterator extends HashIterator
            implements Iterator<V> {
        /**
         * Метод возвращает следующее значение.
         */
        public final V next() {
            return nextNode().value;
        }
    }



    private static class Node<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key)
                    && Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
