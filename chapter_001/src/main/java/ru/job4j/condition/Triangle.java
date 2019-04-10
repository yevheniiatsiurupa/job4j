package ru.job4j.condition;

/**
 * Программа для вычисления площади треугольника.
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 10/04/2019
 */

public class Triangle {
    /**
     * Метод вычисления полупериметра по длинам сторон.
     *
     * Формула.
     *
     * (a + b + c) / 2
     *
     * @param a расстояние между точками a b
     * @param b расстояние между точками a c
     * @param c расстояние между точками b c
     * @return Периметр.
     */
    public double period(double a, double b, double c) {
        return (a + b + c) / 2;
    }

    /**
     * Метод должен вычислить прощадь треугольканива.
     *
     * Формула.
     *
     * √ p *(p - ab) * (p - ac) * (p - bc)
     *
     * где √ - корень квадратный, для извлечения корня использовать метод Math.sqrt().
     *
     * @return Вернуть прощадь, если треугольник существует или -1.
     */

    public double area(int x1, int y1, int x2, int y2, int x3, int y3) {
        double rs1 = -1;
        double a = new Point().distance(x1, y1, x2, y2);
        double b = new Point().distance(x2, y2, x3, y3);
        double c = new Point().distance(x1, y1, x3, y3);
        double p = period(a, b, c);
        if (this.exist(a, b, c)) {
            rs1 = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        }
        return rs1;
    }

    /**
     * Метод проверяет можно ли построить треугольник с такими длинами сторон.
     *
     *
     * @param a Длина от точки a b.
     * @param b Длина от точки a c.
     * @param c Длина от точки b c.
     * @return result
     */
    private boolean exist(double a, double b, double c) {
        boolean result = false;
        if (a + b >= c && a + c >= b && b + c >= a) {
            result = true;
        }
        return result;
    }
}
