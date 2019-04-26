package ru.job4j.paint;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 26/04/2019
 */

public class Triangle implements Shape {
    /**
     * Метод формирует псевдографику треугольника.
     * @return возвращает фигуру треугольника в псевдографике.
     */
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        String ln = System.lineSeparator();
        pic.append("   +   ");
        pic.append(ln);
        pic.append("  +++  ");
        pic.append(ln);
        pic.append(" +++++ ");
        pic.append(ln);
        pic.append("+++++++");
        return pic.toString();
    }
}
