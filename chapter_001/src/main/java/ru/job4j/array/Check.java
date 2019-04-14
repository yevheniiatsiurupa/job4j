package ru.job4j.array;

/**
 * Программа проверяет массив заполнен полностью true или false.
 * @author Evgeniya Tsiurupa.
 * @version 1.0.
 * @since 14/04/19.
 */
public class Check {
    /**
     * Метод проверяет все значения в массиве true или false.
     * @param data исходный массив.
     * @return возвращает true, если все элементы в массиве true или false.
     */
    public boolean mono(boolean[] data) {
        int count = 0;
        for (int i = 0; i != data.length; i++) {
            if (data[i]) {
                count += 1;
            }
        }
        return count == 0 || count == data.length;
    }
}
