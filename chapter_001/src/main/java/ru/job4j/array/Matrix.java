package ru.job4j.array;
/**
 * Программа выводит таблицу умножения заданного размера.
 * @author Evgeniya Tsiurupa.
 * @version 1.0.
 * @since 16/04/19.
 */
public class Matrix {
    /**
     * Метод для вывода таблицы умножения заданного размера.
     * @param size размер таблицы.
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i != table.length; i++) {
            for (int j = 0; j != table[i].length; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}
