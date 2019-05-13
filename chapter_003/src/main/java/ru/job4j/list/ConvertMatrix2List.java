package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 13/05/2019
 */

public class ConvertMatrix2List {
    /**
     * Метод для преобразования двумерного массива в список.
     * @param array исходный массив.
     * @return возвращает список из элементов массива.
     */
    public List<Integer> toList(int[][]array) {
        List<Integer> list = new ArrayList<>();
        for (int[] out : array) {
            for (int inn : out) {
                list.add(inn);
            }
        }
        return list;
    }
}
