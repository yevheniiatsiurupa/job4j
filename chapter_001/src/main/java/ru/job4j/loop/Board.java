package ru.job4j.loop;
/**
 * Программа для построения шахматной доски.
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 12/04/2019
 */
public class Board {
    /**
     * Метод для создания шахматной доски.
     * @param width ширина доски.
     * @param height высота доски.
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}
