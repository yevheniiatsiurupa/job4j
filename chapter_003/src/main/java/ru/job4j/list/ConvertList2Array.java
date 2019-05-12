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
        if(list.size() % rows ==0) {
            columns = list.size() / rows;
        } else {
            columns = list.size() / rows + 1;
        }
        int[][] result = new int[rows][columns];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if (count < list.size()){
                    result[i][j] = list.get(count);
                    count++;
                } else {
                    result[i][j] = 0;
                }

            }
        }
        return result;
    }
}
