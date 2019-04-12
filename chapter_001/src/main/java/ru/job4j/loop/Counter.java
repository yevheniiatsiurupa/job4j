package ru.job4j.loop;

/**
 * Программа для подсчета суммы четных чисел в диапазоне.
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 10/04/2019
 */
public class Counter {

    /**
     * Метод для вычисления суммы четных чисел.
     * @param start the first number of the interval.
     * @param finish the last number of the interval.
     * @return sum the sum of even numbers.
     */

    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
          if (i % 2 == 0) {
              sum += i;
          }
        }
        return sum;
    }
}
