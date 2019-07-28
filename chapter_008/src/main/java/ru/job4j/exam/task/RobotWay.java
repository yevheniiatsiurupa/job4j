package ru.job4j.exam.task;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0.
 * @since 28/07/2019.
 * @author Evgeniya Tsiurupa
 */

public class RobotWay {
    /**
     * Метод для поиска кратчайшего пути робота.
     * Поле заполнено нулями и единицами. Робот может ходить только по единицам.
     * Создаем массив для записи результатов алгоритма (номеров волн).
     * Добавляем стартовый узел в список текущей волны, проставляем в него единицу (первая волна).
     * Запускаем цикл, пока волна не дойдет до финишного узла.
     * Перебираем все узлы текущей волны, которые проходимые (не равны 0 в начальном поле).
     * Добавляем все соседние узлы в список узлов следующей волны.
     * Заполняем узлы следующей волны ее номером, если они проходимые и не были заполнены ранее.
     *
     * Для получения списка узлов кратчайшего пути начинаем двигаться от финишного узла.
     * Находим среди его соседних узлов узел с номером волны на 1 меньше текущего.
     * Добавляем его в список, он сам становится текущим узлом (и так до стартового).
     *
     * Для вывода кратчаешего пути на экран берем поле и просталяем "8" в узлах, которые попали в список
     * кратчайшего пути.
     *
     * @param board массив поля.
     * @param sRow координата х старта.
     * @param sCol координата у старта.
     * @param fRow координата х финиша.
     * @param fCol координата у финиша.
     * @return возвращает количество шагов кратчайшего пути (он обозначен цифрами 8).
     */
    public int minWay(int[][] board, int sRow, int sCol, int fRow, int fCol) {
        int[][] resultBoard = new int[board.length][board[0].length];
        int waveLevel = 1;
        List<Point> wave = new ArrayList<>();
        wave.add(new Point(sRow, sCol));
        resultBoard[sRow][sCol] = 1;
        while (resultBoard[fRow][fCol] == 0) {
            List<Point> nextWave = new ArrayList<>();
            for (Point tmp : wave) {
                if (resultBoard[tmp.getRow()][tmp.getCol()] != 0) {
                    nextWave.addAll(this.getNear(tmp.getRow(), tmp.getCol(), board));
                }
            }
            for (Point tmp2 : nextWave) {
                int row = tmp2.getRow();
                int col = tmp2.getCol();
                if (board[row][col] == 1 && resultBoard[row][col] == 0) {
                    resultBoard[row][col] = waveLevel + 1;
                }
            }
            wave = nextWave;
            waveLevel++;
        }

        List<Point> resWay = new ArrayList<>();
        Point currPoint = new Point(fRow, fCol);
        int currLevel = resultBoard[fRow][fCol];
        resWay.add(currPoint);
        while (currLevel != 1) {
            List<Point> near = this.getNear(currPoint.getRow(), currPoint.getCol(), resultBoard);
            for (Point tmp : near) {
                if (resultBoard[tmp.getRow()][tmp.getCol()] == currLevel - 1) {
                    currPoint = new Point(tmp.getRow(), tmp.getCol());
                    resWay.add(currPoint);
                    currLevel--;
                    break;
                }
            }
        }

        for (Point tmp : resWay) {
            board[tmp.getRow()][tmp.getCol()] = 8;
        }
        System.out.println("The shortest way:");
        this.printBoard(board);
        return 0;
    }

    /**
     * Метод для вывода поля на печать.
     * @param board исходное поле.
     */
    public void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * Метод для получения списка точек, соседних для данной точки.
     * @param row ряд исходной точки.
     * @param col столбец исходной точки.
     * @param board поле.
     * @return возвращает список соседних точек (учитывает положение точки: угол, край, центр)
     */
    private List<Point> getNear(int row, int col, int[][] board) {
        List<Point> result = new ArrayList<>();
        int height = board.length;
        int width = board[0].length;
        if (row > 0 && row < height - 1 && col > 0 && col < width - 1) {
            result.add(new Point(row + 1, col));
            result.add(new Point(row - 1, col));
            result.add(new Point(row, col + 1));
            result.add(new Point(row, col - 1));
        } else {
            if (row == 0 || row == height - 1) {
                if (row == 0) {
                    result.add(new Point(row + 1, col));
                } else {
                    result.add(new Point(row - 1, col));
                }
                if (col == 0) {
                    result.add(new Point(row, col + 1));
                } else if (col == width - 1) {
                    result.add(new Point(row, col - 1));
                } else {
                    result.add(new Point(row, col + 1));
                    result.add(new Point(row, col - 1));
                }
            } else {
                result.add(new Point(row + 1, col));
                result.add(new Point(row - 1, col));
                if (col == 0) {
                    result.add(new Point(row, col + 1));
                } else {
                    result.add(new Point(row, col - 1));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] board = {
                {1, 1, 1, 1, 0, 1},
                {1, 1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1},
                {0, 1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1, 1}
        };

        RobotWay rw = new RobotWay();
        System.out.println("Initial board:");
        rw.printBoard(board);
        rw.minWay(board, 4, 0, 0, 5);

    }

    /**
     * Класс, обозначающий точку на поле.
     * У точки есть ряд и столбец.
     */
    public static class Point {
        private int row;
        private int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
}
