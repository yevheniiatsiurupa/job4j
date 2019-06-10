package ru.job4j.list;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 09/06/2019
 */

public class LoopSearch<E> {

    /**
     * Метод проверяет наличие цикла в односвязном списке.
     * Последний элемент односвязного нецикличного списка должен
     * указывать на нулевой элемент, а не на другой элемент списка.
     * Для проверки запускаем два указателя с разной скоростью.
     *
     * slow - смещается на один элемент списка с каждой итерацией.
     * fast - смещается на два элемента списка с каждой итерацией.
     *
     * Если цикл отсутствует, то второй указатель будет в определенной
     * итерации указывать на ноль.
     * Если цикл есть, тогда указатели будут указывать на одинаковый элемент
     * на определенной итерации.
     *
     * @param first первый элемент односвязного списка.
     * @return возвращает есть ли циклические ссылки в списке.
     */
    public boolean hasCycle(Node<E> first) {
        if (first == null) {
            return false;
        }
        Node<E> slow;
        Node<E> fast;
        slow = first;
        fast = first;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static class Node<E> {
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