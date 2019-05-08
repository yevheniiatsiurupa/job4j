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
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    /**
     * Метод определяет возможные варианты хода фигуры и возвращает массив ячеек, которые пройдет фигура
     * от source до desc, если она ходит верно (только по диагоналям).
     * deltaX, deltaY параметры, которые помогают определить, ходит ли фигура по диагонали.
     * При делении deltaX / Y на их абсолютные значения можно определить направление движения (по знакам).
     * @param source начальная ячейка фигуры.
     * @param dest конечная ячейка, в которую мы собираемся потом скопировать фигуру после допустимого хода.
     * @return возвращает массив ячеек, которые пройдет фигура.
     */
    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps;
        int deltaX = dest.x - source.x;
        int deltaY = dest.y - source.y;
        if (Math.abs(deltaX) != Math.abs(deltaY)) {
            throw new ImpossibleMoveException("Impossible move");
        }
        int size = Math.abs(deltaX);

        steps = new Cell[size];
        int signX = deltaX / Math.abs(deltaX);
        int signY = deltaY / Math.abs(deltaY);

        for (int i = 0; i < Math.abs(deltaX); i++) {
            steps[i] = Cell.findByCoord(source.x + signX * (i + 1), source.y + signY * (i + 1));
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
