package ru.job4j.paint;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 26/04/2019
 */

public class Square implements Shape {
    /**
     * Метод формирует псевдографику квадрата.
     * @return возвращает фигуру квадрата в псевдографике.
     */
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        String ln = System.lineSeparator();
        pic.append("++++");
        pic.append(ln);
        pic.append("+  +");
        pic.append(ln);
        pic.append("+  +");
        pic.append(ln);
        pic.append("++++");
        return pic.toString();
    }
}
