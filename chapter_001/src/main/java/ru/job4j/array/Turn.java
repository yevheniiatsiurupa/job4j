package ru.job4j.array;
/**
 * Программа переворачивает массив.
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 14/04/2019
 */

public class Turn {
    /**
     * Метод, который переворачивает массив.
     * @param array исходный массив.
     * @return возвращает пеервернутый массив.
     */
    public int[] back(int[] array) {
        for (int i = 0; i != array.length / 2; i++) {
            int tmp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = tmp;
        }
        return array;
    }
}
