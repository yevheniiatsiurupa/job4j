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
     * Поле хранит указатель на текущую позицию итератора.
     */
    private int position = -1;

    public EvenNumbersIterator(int[] numbers) {
        this.numbers = numbers;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                position = i;
                break;
            }
        }
    }

    /**
     * Метод для поиска позиции следующего четного числа в списке.
     * @param position исходная позиция в массиве.
     * @return возращает позицию следующего четного числа или исходную позицию.
     */
    public int findNextPosition(int position) {
        int nextPos = numbers.length;
        for (int i = position + 1; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                nextPos = i;
                break;
            }
        }
        return nextPos;
    }

    /**
     * Метод переопределяет аналогичный метод интерфейса Iterator.
     * @return возвращает есть ли еще в списке четные числа.
     */
    @Override
    public boolean hasNext() {
        boolean result = true;
        if (position == -1 || position > numbers.length - 1) {
            result = false;
        }
        return result;
    }

    /**
     * Метод переопределяет аналогичный метод интерфейса Iterator.
     * @return возвращает текущее четное число и переводит указатель на следующее четное число.
     * @throws NoSuchElementException если не найдено четных чисел в массиве или счетчик выходит за пределы массива.
     */
    @Override
    public Object next() throws NoSuchElementException {
        if (position == -1 || position > numbers.length - 1) {
            throw new NoSuchElementException();
        }
        Integer result = numbers[position];
        position = this.findNextPosition(position);
        return result;
    }
}
