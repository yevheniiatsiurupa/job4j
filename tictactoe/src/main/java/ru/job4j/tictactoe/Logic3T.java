package ru.job4j.tictactoe;

import java.util.function.Predicate;

public class Logic3T {
    /**
     * Хранит массив с заполненными клетками.
     */
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    /**
     * Метод проверяет заполнены ли клетки по линии фигурами.
     * @param predicate условие, по которому проверяется клетка.
     * @param startX координата х начальной клетки.
     * @param startY координата у начальной клетки.
     * @param deltaX смещение по х.
     * @param deltaY смещение по у.
     * @return возвращает true / false заполнены ли клетки по условию.
     */
    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Метод проверяет является ли крестик победителем.
     * @return возвращает является ли крестик победителем по комбинациям.
     */
    public boolean isWinnerX() {
        return this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 0)
                || this.fillBy(Figure3T::hasMarkX, 0, 1, 1, 0)
                || this.fillBy(Figure3T::hasMarkX, 0, 2, 1, 0)
                || this.fillBy(Figure3T::hasMarkX, 0, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkX, 1, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkX, 2, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 1)
                || this.fillBy(Figure3T::hasMarkX, this.table.length - 1, 0, -1, 1);
    }

    /**
     * Метод проверяет является ли нолик победителем.
     * @return возвращает является ли нолик победителем по комбинациям.
     */
    public boolean isWinnerO() {
        return this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 0)
                || this.fillBy(Figure3T::hasMarkO, 0, 1, 1, 0)
                || this.fillBy(Figure3T::hasMarkO, 0, 2, 1, 0)
                || this.fillBy(Figure3T::hasMarkO, 0, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkO, 1, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkO, 2, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 1)
                || this.fillBy(Figure3T::hasMarkO, this.table.length - 1, 0, -1, 1);
    }

    /**
     * Метод проверяет все ли клетки заняты либо ноликом, либо крестиком.
     * @return возвращет результат проверки.
     */
    public boolean hasGap() {
        boolean result = false;
        for (Figure3T[] tmp : table) {
            for (Figure3T temp : tmp) {
                if (!temp.hasMarkX() && !temp.hasMarkO()) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
