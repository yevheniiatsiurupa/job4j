package ru.job4j.condition;

/**
 * Программа для вычисления площади треугольника.
 * @author Evgeniya Tsiurupa
 * @version 2.0
 * @since 20/04/2019
 */

public class Triangle {
    /**
     * Поля класса.
     */
    private Point first;
    private Point second;
    private Point third;

    /**
     * Конструктор класса Triangle.
     * @param ap точка 1;
     * @param bp точка 2;
     * @param cp точка 3;
     */
    public Triangle(Point ap, Point bp, Point cp) {
        this.first = ap;
        this.second = bp;
        this.third = cp;
    }

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

    public double area() {
        double rs1 = -1;
        double a = this.first.distance(this.second);
        double b = this.first.distance(this.third);
        double c = this.second.distance(this.third);
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
