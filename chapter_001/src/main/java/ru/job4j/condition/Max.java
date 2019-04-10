package ru.job4j.condition;
/**
 * Программа для поиска максимального числа.
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 10/04/2019
 */

public class Max {
    /**
     * Определяем максимальное число из двух.
     */
    public int max(int left, int right) {
        return left >= right ? left : right;
    }
}
