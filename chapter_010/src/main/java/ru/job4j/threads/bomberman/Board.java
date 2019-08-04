package ru.job4j.threads.bomberman;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0.
 * @since 04/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class Board {
    /**
     * Поле хранит блокировки на клетки поля.
     */
    private final ReentrantLock[][] board;

    /**
     * Поле хранит массив ячеек поля.
     */
    private final Cell[][] cells;

    /**
     * Конструктор.
     * Заполняет массивы board, cells объектами.
     * @param width ширина поля.
     * @param height высота поля.
     */
    public Board(int width, int height) {
        this.board = new ReentrantLock[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        this.cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * Метод для блокировки ячейки поля, когда игрок начинает движение.
     * @param start стартовая ячейка (при создании игрока указываются координаты).
     */
    public void startMove(Cell start) {
        int row = start.getRow();
        int col = start.getCol();
        board[row][col].lock();
    }

    /**
     * Метод для перемещения фигуры с одной ячейки на другую.
     * Игрок пытается занять ячейку dest, если она не заблокирована,
     * то он ее блокирует, а предыдущую source разблокирует.
     * @param source исходная ячейка.
     * @param dest целевая ячейка.
     * @return возвращает результат произошло ли движение.
     */
    public boolean move(Cell source, Cell dest) {
        ReentrantLock sourceLock = board[source.getRow()][source.getCol()];
        ReentrantLock destLock = board[dest.getRow()][dest.getCol()];
        boolean attempt = destLock.tryLock();
        if (attempt) {
            sourceLock.unlock();
        }
        return attempt;
    }

    /**
     * Метод для получения ссылки на ячейку поля по координатам.
     * @param row ряд ячейки.
     * @param col столбец ячейки.
     * @return возвращает ссылку на ячейку.
     */
    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    /**
     * Метод для получения списка соседних ячеек для текущей ячейки.
     * @param source текущая ячейка.
     */
    private List<Cell> getNear(Cell source) {
        int row = source.getRow();
        int col = source.getCol();
        List<Cell> result = new ArrayList<>();
        int height = cells.length;
        int width = cells[0].length;
        if (row > 0 && row < height - 1 && col > 0 && col < width - 1) {
            result.add(new Cell(row + 1, col));
            result.add(new Cell(row - 1, col));
            result.add(new Cell(row, col + 1));
            result.add(new Cell(row, col - 1));
        } else {
            if (row == 0 || row == height - 1) {
                if (row == 0) {
                    result.add(new Cell(row + 1, col));
                } else {
                    result.add(new Cell(row - 1, col));
                }
                if (col == 0) {
                    result.add(new Cell(row, col + 1));
                } else if (col == width - 1) {
                    result.add(new Cell(row, col - 1));
                } else {
                    result.add(new Cell(row, col + 1));
                    result.add(new Cell(row, col - 1));
                }
            } else {
                result.add(new Cell(row + 1, col));
                result.add(new Cell(row - 1, col));
                if (col == 0) {
                    result.add(new Cell(row, col + 1));
                } else {
                    result.add(new Cell(row, col - 1));
                }
            }
        }
        return result;
    }

    /**
     * Метод для получения случайной ячейки из списка ячеек.
     * @param list список ячеек.
     */
    private Cell getRandom(List<Cell> list) {
        int randomNumb = (int) (Math.random() * (list.size()));
        return list.get(randomNumb);
    }

    /**
     * Метод возвращает случайную соседнюю ячейку для source.
     * @param source исходная ячейка.
     */
    public Cell getRandomDest(Cell source) {
        List<Cell> near = this.getNear(source);
        return  this.getRandom(near);
    }
}
