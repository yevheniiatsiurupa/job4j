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

    /**
     * Определяем максимальное число из трех.
     */
    public int max(int one, int two, int three) {
        return max(one, max(two, three));
    }

    /**
     * Определяем максимальное число из четырех.
     */
    public int max(int one, int two, int three, int four) {
        return max(one, max(two, three, four));
    }
}
