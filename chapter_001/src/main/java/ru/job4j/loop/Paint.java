package ru.job4j.loop;
import java.util.function.BiPredicate;
/**
 * Программа для построения пирамиды из символов.
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 13/04/2019
 */

public class Paint {

    /**
     * Метод для постоения правой части треугольника.
     * @param height высота пирамиды.
     */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }


    /**
     * Метод для постоения левой части треугольника.
     * @param height высота пирамиды.
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }

    /**
     * Метод для постоения целой пирамиды.
     * @param height высота пирамиды.
     */
    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }

    private String loopBy(int height, int width, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != width; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}
