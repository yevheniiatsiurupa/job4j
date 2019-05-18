package ru.job4j.sort;

import java.util.Comparator;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 18/05/2019
 */

public class ListCompare implements Comparator<String> {
    /**
     * Метод реализует аналогичный метод класса Comparator.
     * Метод сравнивает строки посимвольно.
     * @param left строка 1.
     * @param right строка 2.
     * @return возвращает результат сравнения.
     */
    @Override
    public int compare(String left, String right) {
        int result = 0;
        int size = left.length() < right.length() ? left.length() : right.length();
        for (int index = 0; index != size; index++) {
            if (left.charAt(index) > right.charAt(index)) {
                result = 1;
                break;
            } else {
                if (left.charAt(index) < right.charAt(index)) {
                    result = -1;
                    break;
                }
            }
        }
        if (result == 0 && left.length() != right.length()) {
            result = left.length() > right.length() ? 1 : -1;
        }
        return result;
    }
}
