package ru.job4j.array;
/**
 * Программа для поиска элемента в массиве.
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 12/04/2019
 */
public class FindLoop {
    /**
     * Метод для поиска элемента в массиве.
     * @param data массив для поиска.
     * @param el искомый элемент.
     * @return возвращает индекс элемента в массиве.
     */
    public int indexOf(int[] data, int el) {
        int rst = -1;
        for (int i = 0; i != data.length; i++) {
            if (data[i] == el) {
                rst = i;
                break;
            }
        }
        return rst;
    }
}
