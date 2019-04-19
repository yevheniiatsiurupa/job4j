package ru.job4j.array;
import java.util.Arrays;
/**
 * Программа удаляет дубликаты в массиве.
 * @author Evgeniya Tsiurupa.
 * @version 1.0.
 * @since 16/04/19.
 */
public class ArrayDuplicate {
    /**
     * Метод удаляет дубликаты в массиве.
     * Count счетчик дублированных значений.
     * @param array исходный массив.
     * @return возвращает массив после сортировки.
     */
    public String[] remove(String[] array) {
        int count = 0;
        for (int i = 0; i <= array.length - 2 - count; i++) {
            for (int j = i + 1; j <= array.length - 1 - count; j++) {
                if (array[i].equals(array[j])) {
                    String temp = array[j];
                    array[j] = array[array.length - 1 - count];
                    array[array.length - 1 - count] = temp;
                    count++;
                    j--;
                }
            }
        }
        return Arrays.copyOf(array, array.length - count);
    }
}
