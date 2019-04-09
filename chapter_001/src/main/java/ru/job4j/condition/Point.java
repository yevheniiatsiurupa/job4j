package ru.job4j.condition;

/**
 * Программа для вычисления расстояния между точками.
 */
public class Point {
    /**
     * Вычисляем расстояние между точками.
     * @param x1 координата х точки 1.
     * @param y1 координата y точки 1.
     * @param x2 координата х точки 2.
     * @param y2 координата y точки 2.
     * @return расстояние между точками 1 и 2.
     */
    public double distance(int x1, int y1, int x2, int y2) {
       double first = Math.pow(x2 - x1, 2);
       double second = Math.pow(y2 - y1, 2);
       return Math.sqrt(first + second);
    }
}
