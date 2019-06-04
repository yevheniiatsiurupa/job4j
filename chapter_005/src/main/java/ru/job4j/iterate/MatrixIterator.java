package ru.job4j.iterate;

import java.util.Iterator;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 04/06/2019
 */

public class MatrixIterator implements Iterator {
    /**
     * Поле хранит двумерный массив.
     */
    private final int[][] values;

    /**
     * Поле хранит текущее значение строки.
     */
    private int row = 0;

    /**
     * Поле хранит текущее значение столбца.
     */
    private int column = 0;

    public MatrixIterator(int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        if ((row == values.length - 1 && values[row].length != 0)
                || (row < values.length - 1 && column <= values[row].length - 1 && values[row].length != 0)) {
            result = true;
        }
        return result;
    }

    @Override
    public Object next() {
        Object result = values[row][column];
        if (column < values[row].length - 1) {
            column++;
        } else {
            row++;
            column = 0;
        }
        return result;
    }
}
