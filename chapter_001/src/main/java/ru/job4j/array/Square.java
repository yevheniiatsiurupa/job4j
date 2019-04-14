package ru.job4j.array;
/**
 * Программа для заполнения массива степенями чисел.
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 14/04/2019
 */

public class Square {
    /**
     * Метод для заполнения массива степенями чисел.
     * @param bound размер массива.
     * @return rst массив с квадратами числе от 1 до bound.
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        for (int i = 0; i != bound; i++) {
            rst [i] = (int) Math.pow(i + 1, 2);
        }
        return rst;
    }
}
