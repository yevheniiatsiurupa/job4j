package ru.job4j.ood;

import java.util.List;

public interface BasicOperation {

        /**
         * Метод возвращает ключ опции.
         * @return ключ.
         */
        String key();

        /**
         * Основной метод.
         * @param numbers список аргументов.
         */
        double perform(List<Double> numbers);

        /**
         * Метод для получения количества аргументов.
         */
        int getArgsNumb();
}
