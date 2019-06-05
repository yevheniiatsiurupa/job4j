package ru.job4j.iterate;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 05/06/2019
 */

public class EvenNumbersIterator implements Iterator {
    /**
     * Поле хранит исходный список произвольных чисел.
     */
    private final int[] numbers;

    /**
     * Поле хранит указатель на текущую позицию итератора (четное число).
     */
    private int position = -1;

    /**
     * Поле хранит указатель на следующую позицию итератора (четное число).
     */
    private int nextEven = -1;

    public EvenNumbersIterator(int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Метод переопределяет аналогичный метод интерфейса Iterator.
     * @return возвращает есть ли еще в списке четные числа.
     */
    @Override
    public boolean hasNext() {
        boolean result = true;
        for (int i = position + 1; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                nextEven = i;
                break;
            }
        }
        if (nextEven == -1 || nextEven == position) {
            result = false;
        }
        return result;
    }

    /**
     * Метод переопределяет аналогичный метод интерфейса Iterator.
     * @return возвращает четное число.
     * @throws NoSuchElementException если не найдено четных чисел в массиве или счетчик выходит за пределы массива.
     */
    @Override
    public Object next() throws NoSuchElementException {
        this.hasNext();
        if (nextEven == -1 || nextEven == position) {
            throw new NoSuchElementException();
        }
        position = nextEven;
        return numbers[position];
    }
}
