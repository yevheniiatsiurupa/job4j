package ru.job4j.exam.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Board {
    /**
     * Размер поля для игры.
     */
    private int size;

    /**
     * Массив строк хранит поле в псевдографике.
     */
    private String[][] board;

    /**
     * Поле хранит символ, который используется для заполнения пустых клеток.
     */
    private String emptySign;

    /**
     * Список хранит номера свободных ячеек.
     * Начальная нумерация ячеек идет справа - налево, сверху - вниз от нуля size в квадрате.
     */
    public List<Integer> emptyCell = new ArrayList<>();

    /**
     * Конструктор.
     * @param size размер поля для игры.
     * @param emptySign символ для незаполненных ячеек.
     */
    public Board(int size, String emptySign) {
        this.size = size;
        this.board = new String[size][size];
        this.emptySign = emptySign;
    }

    public int getSize() {
        return size;
    }

    public String getEmptySign() {
        return emptySign;
    }

    /**
     * Метод для получения уникального номера ячейки по номеру строки и столбца.
     * @param row номер строки (с нуля).
     * @param column номер столбца (с нуля).
     */
    public int getCellNumber(int row, int column) {
        return row * this.size + column;
    }


    /**
     * Метод заполняет поле знаками для пустых ячеек.
     * Заполняет список свободных ячеек.
     * Вызывается в начале игры.
     */
    public void fillBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = emptySign;
                int cellNumb = this.getCellNumber(i, j);
                this.emptyCell.add(cellNumb);
            }
        }
    }


    /**
     * Метод показывает заполненное поле.
     */
    public void showBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * Метод заносит крестик или нолик на поле по номеру строки и столбца.
     * Ячейка, которую занимает знак, удаляется из списка свободных ячеек.
     * @param row номер строки.
     * @param column номер столбца.
     * @param sign знак, который заносится на поле.
     */
    public void drawSign(int row, int column, String sign) {
        this.board[row][column] = sign;
        Integer numb = this.getCellNumber(row, column);
        this.emptyCell.remove(numb);
    }

    /**
     * Метод заносит крестик или нолик на поле по номеру ячейки.
     * Ячейка, которую занимает знак, удаляется из списка свободных ячеек.
     * @param cellNumb номер ячейки.
     * @param sign знак, который заносится на поле.
     */
    public void drawSignByCell(int cellNumb, String sign) {
        int row = cellNumb / size;
        int column = cellNumb % size;
        this.board[row][column] = sign;
        this.emptyCell.remove((Integer) cellNumb);
    }

    /**
     * Метод добавляет знак на поле в случайную ячейку среди предложенных.
     * @param possibleCells список ячеек для выбора хода.
     * @param sign знак для записи.
     */
    public void drawRandom(List<Integer> possibleCells, String sign) {
        int randomNumb = (int) (Math.random() * (possibleCells.size() - 1));
        int cellNumber = possibleCells.get(randomNumb);
        int row = cellNumber / size;
        int column = cellNumber % size;
        this.drawSign(row, column, sign);
    }

    public List<List<String>> getRowsList() {
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<String> rowLine = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                rowLine.add(board[i][j]);
            }
            result.add(rowLine);
        }
        return result;
    }

    public List<List<String>> getColumnsList() {
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<String> columnLine = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                columnLine.add(board[j][i]);
            }
            result.add(columnLine);
        }
        return result;
    }

    public List<List<String>> getDiagonalsList() {
        List<List<String>> result = new ArrayList<>();
        List<String> diag1 = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            diag1.add(board[i][i]);
        }
        result.add(diag1);
        List<String> diag2 = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            diag2.add(board[size - i - 1][i]);
        }
        result.add(diag2);
        return result;
    }

}
