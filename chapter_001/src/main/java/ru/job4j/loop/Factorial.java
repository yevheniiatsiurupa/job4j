package ru.job4j.loop;

/**
 * Программа для подсчета факториала числа.
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 12/04/2019
 */
public class Factorial {
    /**
     * Метод для вычисления факториала.
     */
    public int calc(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
