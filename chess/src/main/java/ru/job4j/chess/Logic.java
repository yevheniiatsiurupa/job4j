package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Optional;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    /**
     * Метод проверяет возможность хода фигуры.
     * @param source начальная координата фигуры.
     * @param dest конечная координата фигуры.
     * Cell[] steps массив ячеек, которые проходит фигура.
     * @return возвращает true, если ход возможен, и выкидывает исключения, если невозможен.
     * @throws ImpossibleMoveException исключение если направление хода неверное.
     * @throws OccupiedWayException исключение если на пути есть фигура.
     * @throws FigureNotFoundException исключение если исходная фигура не найдена.
     */
    public boolean move(Cell source, Cell dest)
            throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean rst = false;
        int index = this.findBy(source);
        if (index != -1) {
            Cell[] steps = this.figures[index].way(source, dest);
            if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                boolean occupied = false;
                for (int pos = 0; pos < steps.length - 1; pos++) {
                    if (this.findBy(steps[pos]) != -1) {
                        occupied = true;
                        break;
                    }
                }
                if (!occupied) {
                    rst = true;
                    this.figures[index] = this.figures[index].copy(dest);
                } else {
                    throw new OccupiedWayException("Cell is occupied");
                }
            } else {
                throw new ImpossibleMoveException("Impossible move");
            }
        } else {
            throw new FigureNotFoundException("Figure is not found");
        }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
