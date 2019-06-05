package ru.job4j.iterate;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    /**
     * Метод преобразует итератор итераторов чисел в итератор чисел.
     * @param it входящий итератор итераторов.
     * @return возвращает итератор чисел.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            /**
             * Поле хранит текущий итератор.
             */
            private Iterator<Integer> current = it.next();

            @Override
            public boolean hasNext() {
                while (it.hasNext() && !current.hasNext()) {
                    current = it.next();
                }
                return current.hasNext();
            }

            @Override
            public Integer next() throws NoSuchElementException {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return current.next();
            }
        };
    }
}
