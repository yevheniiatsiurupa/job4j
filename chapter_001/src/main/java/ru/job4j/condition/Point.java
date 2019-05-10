package ru.job4j.condition;

/**
 * Программа для вычисления расстояния между точками.
 * @author Evgeniya Tsiurupa
 * @version 2.0
 * @since 20/04/2019
 */
public class Point {
    /**
     * Поле объекта.
     */
    private int x;

    /**
     * Поле объекта.
     */
    private int y;

    /**
     * Поле объекта.
     */
    private int z;
    /**
     * Конструкор, который принимает начальное состояние объекта.
     * @param first координата х.
     * @param second координата у.
     */
    public Point(int first, int second) {
        this.x = first;
        this.y = second;
    }

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Вычисляем расстояние между точками.
     * @param that объект класса Point с двумя координатами.
     * @return расстояние между точками 1 и 2.
     */
    public double distance(Point that) {
       double first = Math.pow(this.x - that.x, 2);
       double second = Math.pow(this.y - that.y, 2);
       return Math.sqrt(first + second);
    }

    /**
     * Вычисляем расстояние между точками в пространстве.
     * @param that объект класса Point с двумя координатами.
     * @return расстояние между точками 1 и 2.
     */
    public double distance3d(Point that) {
        double first = Math.pow(this.x - that.x, 2);
        double second = Math.pow(this.y - that.y, 2);
        double third = Math.pow(this.z - that.z, 2);
        return Math.sqrt(first + second + third);
    }

    /**
     * Метод для вывода значений полей х и у.
     */
    public void info() {
        System.out.println(String.format("Point[%s, %s]", this.x, this.y));
    }
}
