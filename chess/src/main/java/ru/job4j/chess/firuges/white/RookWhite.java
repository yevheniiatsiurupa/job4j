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
public class RookWhite implements Figure {
    private final Cell position;

    public RookWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    /**
     * Метод определяет возможные варианты хода фигуры и возвращает массив ячеек, которые пройдет фигура
     * от source до desc, если она ходит верно (под углом 90 градусов).
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
        if (Math.abs(deltaX) > 0 && Math.abs(deltaY) > 0) {
            throw new ImpossibleMoveException("Impossible move");
        }
        int size = Math.abs(deltaX - deltaY);
        steps = new Cell[size];

        boolean right = deltaX > 0;
        boolean left = deltaX < 0;
        boolean up = deltaY > 0;

        if (right) {
            for (int i = 0; i < deltaX; i++) {
                steps[i] = Cell.findByCoord(source.x + i + 1, source.y);
            }
        } else if (left) {
            for (int i = 0; i < Math.abs(deltaX); i++) {
                steps[i] = Cell.findByCoord(source.x - i - 1, source.y);
            }
        } else if (up) {
            for (int i = 0; i < deltaY; i++) {
                steps[i] = Cell.findByCoord(source.x, source.y  + i + 1);
            }
        } else {
            for (int i = 0; i < Math.abs(deltaY); i++) {
                steps[i] = Cell.findByCoord(source.x, source.y  - i - 1);
            }
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookWhite(dest);
    }
}
