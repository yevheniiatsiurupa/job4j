package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 28/05/2019
 */

public class MatrixToList {
    /**
     * Метод преобразует матрицу в список чисел.
     * @param input исходный массив чисел.
     * @return возвращает список чисел.
     */
    List<Integer> convertMatrix(Integer[][] input) {
        return Arrays.stream(input).flatMap(e -> Arrays.stream(e)).collect(Collectors.toList());
    }
}
