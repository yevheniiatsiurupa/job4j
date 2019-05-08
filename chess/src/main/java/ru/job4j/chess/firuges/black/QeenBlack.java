package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class QeenBlack implements Figure {
    private final Cell position;

    public QeenBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }


    /**
     * Метод определяет возможные варианты хода фигуры и возвращает массив ячеек, которые пройдет фигура
     * от source до desc, если она ходит верно (только по диагоналям или под углом 90 градусов).
     * deltaX, deltaY параметры, которые помогают определить направление движения фигуры.
     * right, up, left определяют направления движения.
     * @param source начальная ячейка фигуры.
     * @param dest конечная ячейка, в которую мы собираемся потом скопировать фигуру после допустимого хода.
     * @return возвращает массив ячеек, которые пройдет фигура.
     */
    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        int deltaX = dest.x - source.x;
        int deltaY = dest.y - source.y;

        if (Math.abs(deltaX) > 0 && Math.abs(deltaY) > 0 && Math.abs(deltaX) != Math.abs(deltaY)) {
            throw new ImpossibleMoveException("Impossible move");
        }

        boolean diagonal = Math.abs(deltaX) == Math.abs(deltaY);
        boolean right = deltaX > 0;
        boolean left = deltaX < 0;
        boolean up = deltaY > 0;

        int size;

        if (diagonal) {
            int signX = deltaX / Math.abs(deltaX);
            int signY = deltaY / Math.abs(deltaY);
            size = Math.abs(deltaX);
            steps = new Cell[size];
            for (int i = 0; i < Math.abs(deltaX); i++) {
                steps[i] = Cell.findByCoord(source.x + signX * (i + 1), source.y + signY * (i + 1));
            }
        } else if (right) {
            size = Math.abs(deltaX - deltaY);
            steps = new Cell[size];
            for (int i = 0; i < deltaX; i++) {
                steps[i] = Cell.findByCoord(source.x + i + 1, source.y);
            }
        } else if (left) {
            size = Math.abs(deltaX - deltaY);
            steps = new Cell[size];
            for (int i = 0; i < Math.abs(deltaX); i++) {
                steps[i] = Cell.findByCoord(source.x - i - 1, source.y);
            }
        } else if (up) {
            for (int i = 0; i < deltaY; i++) {
                size = Math.abs(deltaX - deltaY);
                steps = new Cell[size];
                steps[i] = Cell.findByCoord(source.x, source.y  + i + 1);
            }
        } else {
            size = Math.abs(deltaX - deltaY);
            steps = new Cell[size];
            for (int i = 0; i < Math.abs(deltaY); i++) {
                steps[i] = Cell.findByCoord(source.x, source.y  - i - 1);
            }
        }

        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new QeenBlack(dest);
    }
}
