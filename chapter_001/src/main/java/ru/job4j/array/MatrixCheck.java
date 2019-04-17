package ru.job4j.array;
/**
 * Программа проверяет, что обе диагонали массива true or false.
 * @author Evgeniya Tsiurupa.
 * @version 1.0.
 * @since 16/04/19.
 */
public class MatrixCheck {
    /**
     * Метод проеряет, что элементы двух диагоналей все true or false.
     * @param data исходный массив.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 1; i != data.length; i++) {
            if (data[i][i] != data[0][0] || data[data.length - 1 - i][i] != data[data.length - 1][0]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
