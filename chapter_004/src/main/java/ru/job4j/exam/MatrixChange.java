package ru.job4j.exam;

import java.util.Arrays;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 07/06/2019
 */

public class MatrixChange {
    /**
     * Метод производит действия над элементами и возвращает результат вычислений.
     * @param input входящий массив.
     * @return возвращает результат вычислений.
     */
    public int changeMatrix(int[] input) {
        return Arrays.stream(input)
                .filter(x -> x % 2 == 0)
                .map(x -> (int) Math.pow(x, 2))
                .reduce(0, (s1, s2) -> s1 + s2);
    }
}
