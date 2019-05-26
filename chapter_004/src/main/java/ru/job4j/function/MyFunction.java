package ru.job4j.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Программа для подсчета функции в диапазоне.
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 26/05/2019
 */

public class MyFunction {
    /**
     * Метод для подсчета функции в диапазоне.
     * @param start начальное значение диапазона для аргумента.
     * @param end конечное значение диапазона для аргумента.
     * @param func функция реализует функциональный интерфейс Function.
     *             Выполняет операцию над объектом типа Double, возращает Double.
     *             Принимает значение аргумента из указанного диапазона.
     * @return возвращает список значений функций, вычисленных для данного диапазона аргументов.
     */
    List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int index = start; index != end + 1; index++) {
            result.add(func.apply((double) index));
        }
        return result;
    }

}
