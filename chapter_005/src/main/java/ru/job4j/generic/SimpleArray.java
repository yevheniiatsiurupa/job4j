package ru.job4j.generic;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 08/06/2019
 */

public class SimpleArray<T> implements Iterable<T> {
    /**
     * Поле хранит внутренний массив.
     */
    private Object[] array;

    /**
     * Поле хранит количество занятых ячеек в массиве.
     */
    private int position = 0;

    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    /**
     * Метод для добавления объекта в массив.
     * @param model объект, который добавляется.
     */
    public void add(T model) {
        if (position >= array.length) {
            throw new RuntimeException("Array is full");
        }
        if (model != null) {
            array[position++] = model;
        }

    }

    /**
     * Метод для изменения объекта в массиве.
     * @param index индекс изменяемого элемента.
     * @param model объект, на который меняется элемент массива.
     * @return возвращает результат исхода операции.
     */
    public boolean set(int index, T model) {
        boolean result = false;
        if (index >= 0 && index < position) {
            array[index] = model;
            result = true;
        }
        return result;
    }

    /**
     * Метод для удаления объекта в массиве.
     * @param index индекс удаляемого элемента.
     * @return возвращает результат исхода операции.
     */
    public boolean remove(int index) {
        boolean result = false;
        if (index >= 0 && index < array.length) {
            System.arraycopy(array, index + 1, array, index, position - index - 1);
            array[position - 1] = null;
            position--;
            result = true;
        }
        return result;
    }

    /**
     * Метод возвращает элемент по индексу.
     * @param index индекс искомого элемента.
     * @return возвращает элемент по индексу.
     */
    public T get(int index) {
        return (T) this.array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentPos = 0;
            @Override
            public boolean hasNext() {
                return currentPos < position;
            }

            @Override
            public T next() {
                return (T) array[currentPos++];
            }
        };
    }

    public <T extends Base> T findById(String id) {
        T result = null;
        for (Object tmp : array) {
            T temp = (T) tmp;
            if (temp != null && temp.getId().equals(id)) {
                result = temp;
                break;
            }
        }
        return result;
    }

    public <T extends Base> T[] findAll() {
        T[] result = (T[]) array;
        return Arrays.copyOf(result, position);
    }
}
