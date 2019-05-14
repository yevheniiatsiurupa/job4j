package ru.job4j.list;

import java.util.List;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 12/05/2019
 */

public class ConvertList2Array {
    /**
     * Метод преобразует список в двумерный массив.
     * @param list исходный список.
     * @param rows количество строк массива.
     * @return возвращает массив.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int columns;
        if (list.size() % rows == 0) {
            columns = list.size() / rows;
        } else {
            columns = list.size() / rows + 1;
        }
        int[][] result = new int[rows][columns];
        int i = 0;
        int j = 0;
        for (int tmp : list) {
            result[i][j] = tmp;
            j++;
            if (j > columns - 1) {
                j = 0;
                i++;
            }
        }
        return result;
    }
}
