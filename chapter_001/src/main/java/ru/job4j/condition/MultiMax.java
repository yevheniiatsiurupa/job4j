package ru.job4j.condition;
/**
 * Программа для поиска максимального числа из трех чисел.
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 10/04/2019
 */
public class MultiMax {
    /**
     * Определяем максимальное число из трех чисел.
     */
    public int max(int first, int second, int third) {
        int result = first;
        result = result > second ? first : second;
        result = result > third ? result : third;
        return result;
    }
}
