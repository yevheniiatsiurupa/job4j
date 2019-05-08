package ru.job4j.chess.firuges.white;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class KingWhite implements Figure {
    private final Cell position;

    public KingWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    /**
     * Метод определяет возможные варианты хода фигуры и возвращает массив ячеек, которые пройдет фигура
     * от source до desc, если она ходит верно (только на 1 клетку).
     * deltaX, deltaY параметры, которые помогают определить направление движения фигуры.
     * right, up, left определяют направления движения.
     * @param source начальная ячейка фигуры.
     * @param dest конечная ячейка, в которую мы собираемся потом скопировать фигуру после допустимого хода.
     * @return возвращает массив ячеек, которые пройдет фигура.
     */
    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps;
        int deltaX = dest.x - source.x;
        int deltaY = dest.y - source.y;

        if (!(Math.abs(deltaX) <= 1 && Math.abs(deltaY) <= 1)) {
            throw new ImpossibleMoveException("Impossible move");
        }

        boolean diagonal = Math.abs(deltaX) == Math.abs(deltaY);
        boolean right = deltaX > 0;
        boolean left = deltaX < 0;
        boolean up = deltaY > 0;

        steps = new Cell[1];

        if (diagonal) {
            int signX = deltaX / Math.abs(deltaX);
            int signY = deltaY / Math.abs(deltaY);
            steps[0] = Cell.findByCoord(source.x + signX, source.y + signY);
        } else if (right) {
            steps[0] = Cell.findByCoord(source.x + 1, source.y);
        } else if (left) {
            steps[0] = Cell.findByCoord(source.x - 1, source.y);
        } else if (up) {
            steps[0] = Cell.findByCoord(source.x, source.y + 1);
        } else {
            steps[0] = Cell.findByCoord(source.x, source.y - 1);
        }

        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingWhite(dest);
    }
}
