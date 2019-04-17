package ru.job4j.array;
/**
 * Программа для сортировки массива методом перестановки.
 * @author Evgeniya Tsiurupa.
 * @version 1.0.
 * @since 16/04/19.
 */
public class BubbleSort {
    /**
     * Метод для сортировки.
     * @param array исходный массив.
     * @return array отсортированный массив.
     */
    public int[] sort(int[] array) {
        for (int i = 0; i != array.length - 1; i++) {
            for (int j = 0; j != array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }
}
