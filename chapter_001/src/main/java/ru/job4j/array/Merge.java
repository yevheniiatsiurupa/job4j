package ru.job4j.array;

import java.util.Arrays;

/**
 * Программа для подсчета факториала числа.
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 18/04/2019
 */
public class Merge {
    /**
     * Метод для соединения двух массив в один отсортированный.
     * @param left массив 1.
     * @param right массив 2.
     * @return возвращает объединенный массив.
     */
    public int[] merge(int[] left, int[] right) {
        int[] rsl = new int[left.length + right.length];
        //индекс сравниваемых значений в массиве 1 и массиве 2.
        int i = 0;
        int j = 0;
        for (int k = 0; k < rsl.length; k++) {
            //проверка, что индекс массива 1 не превышает предельного значения.
            if (i > left.length - 1) {
                rsl[k] = right[j];
                j++;
            }
            //проверка, что индекс массива 2 не превышает предельного значения.
            else if (j > right.length - 1) {
                rsl[k] = left[i];
                i++;
            }
            //запись в результирующий массив меньшего из двух чисел.
            else if (left[i] < right[j]) {
                rsl[k] = left [i];
                i++;
            }
            else {
                rsl [k] = right [j];
                j++;
            }
        }
        return rsl;
    }
}
