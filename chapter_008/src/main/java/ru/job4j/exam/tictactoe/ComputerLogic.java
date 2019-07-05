package ru.job4j.exam.tictactoe;

import java.util.ArrayList;
import java.util.List;


public class ComputerLogic {
    /**
     * Ссылка на игровое поле.
     */
    private Board board;

    /**
     * Поле хранит знак компьютера (крестик / нолик).
     */
    private String compSign;

    /**
     * Поле хранит знак пользователя (крестик / нолик).
     */
    private String userSign;

    /**
     * Поле хранит информацию первый ли ход сейчас.
     */
    private boolean firstMove = true;

    /**
     * Конструктор.
     * Знак компьютера выбирается в зависимости от знака пользователя.
     */
    public ComputerLogic(Board board, UserLogic userLogic) {
        this.board = board;
        this.compSign = userLogic.getCompSign();
        this.userSign = userLogic.getUserSign();
    }

    /**
     * Метод для добавления фигуры, если это первый ход компьютера.
     */
    private void drawFirst() {
        this.board.drawRandom(this.board.emptyCell, this.compSign);
    }

    /**
     * Метод для добавления фигуры, если это очередной ход компьютера.
     * Добавление фигуры происходит в первую очередь туда, где есть почти выигрышная линия противника.
     * Во вторую очередь туда, где самое большое количество фигур компьютера в строке и нет фигур проитивника.
     * Если такие ячейки не найдены, то добавляем в случайную пустую ячейку.
     */
    private void drawNext() {
        int userMoves = this.checkUserMoves();
        if (userMoves != -1) {
            this.board.drawSignByCell(userMoves, this.compSign);
        } else {
            int bestCompMove = this.findBestCompMove();
            if (bestCompMove != -1) {
                this.board.drawSignByCell(bestCompMove, this.compSign);
            } else {
                this.board.drawRandom(this.board.emptyCell, this.compSign);
            }
        }
    }

    /**
     * Метод для хода компьютера.
     */
    public void drawCompSign() {
        if (firstMove) {
            this.drawFirst();
            firstMove = false;
        } else {
            this.drawNext();
        }
    }

    /**
     * Метод ищет линии поля с фигурами пользователя, где не хватает одной фигуры до выигрыша.
     * @return возвращает номер пустой ячейки в этой линии.
     */
    private int checkUserMoves() {
        List<List<String>> rows = this.board.getRowsList();
        for (int i = 0; i < rows.size(); i++) {
            List<String> tmpRow = rows.get(i);
            int cellNumb = this.findUserWin(tmpRow);
            if (cellNumb != -1) {
                return this.board.getCellNumber(i, cellNumb);
            }
        }

        List<List<String>> columns = this.board.getColumnsList();
        for (int i = 0; i < rows.size(); i++) {
            List<String> tmpColumn = columns.get(i);
            int cellNumb = this.findUserWin(tmpColumn);
            if (cellNumb != -1) {
                return this.board.getCellNumber(cellNumb, i);
            }
        }

        List<List<String>> diagonals = this.board.getDiagonalsList();
        List<String> diag1 = diagonals.get(0);
        int cellNumb = this.findUserWin(diag1);
        if (cellNumb != -1) {
            return this.board.getCellNumber(cellNumb, cellNumb);
        }
        List<String> diag2 = diagonals.get(1);
        cellNumb = this.findUserWin(diag2);
        if (cellNumb != -1) {
            return this.board.getCellNumber(this.board.getSize() - cellNumb - 1, cellNumb);
        }
        return -1;
    }

    /**
     * Метод проверяет строку из поля.
     * Если входящая строка содержит только одну пустую ячейку, а остальные - фигуры пользователя,
     * то возвращается индекс пустой ячейки.
     * Если условие не выполняется, возращается -1.
     * @param line входящая строка поля.
     * @return номер пустой ячейки (при условии, что остальные - пользователя) или -1.
     */
    private int findUserWin(List<String> line) {
        int result = -1;
        int emptyCells = 0;
        int compCells = 0;
        for (String tmp : line) {
            if (tmp.equals(this.board.getEmptySign())) {
                emptyCells++;
            }
            if (tmp.equals(this.compSign)) {
                compCells++;
                break;
            }
        }
        if (emptyCells == 1 && compCells == 0) {
            result = line.indexOf(this.board.getEmptySign());
        }
        return result;
    }

    /**
     * Метод возвращает количество знаков компьютера в строке при условии, что там нет знаков пользователя.
     * @param line входящая строка поля.
     * @return возвращает максимум или -1.
     */
    private int findMaxCompSign(List<String> line) {
        int result = -1;
        int userCells = 0;
        int compCells = 0;
        for (String tmp : line) {
            if (tmp.equals(this.userSign)) {
                userCells++;
            }
            if (tmp.equals(this.compSign)) {
                compCells++;
            }
        }
        if (userCells == 0) {
            result = compCells;
        }
        return result;
    }

    /**
     * Метод возвращает радномный индекс пустой ячейки.
     * @param line входящая строка поля.
     */
    private int findRandomEmpty(List<String> line) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < line.size(); i++) {
            String tmp = line.get(i);
            if (tmp.equals(this.board.getEmptySign())) {
                result.add(i);
            }
        }
        int randomNumb = (int) (Math.random() * (result.size() - 1));
        return result.get(randomNumb);
    }

    private int findBestCompMove() {
        int max = -1;
        int row = -1;
        int column = -1;

        List<List<String>> rows = this.board.getRowsList();
        for (int i = 0; i < rows.size(); i++) {
            List<String> tmpRow = rows.get(i);
            int tmpMax = this.findMaxCompSign(tmpRow);
            if (tmpMax > max) {
                max = tmpMax;
                row = i;
                column = this.findRandomEmpty(tmpRow);
            }
        }

        List<List<String>> columns = this.board.getColumnsList();
        for (int i = 0; i < rows.size(); i++) {
            List<String> tmpColumn = columns.get(i);
            int tmpMax = this.findMaxCompSign(tmpColumn);
            if (tmpMax > max) {
                max = tmpMax;
                row = this.findRandomEmpty(tmpColumn);
                column = i;
            }
        }

        List<List<String>> diagonals = this.board.getDiagonalsList();
        List<String> diag1 = diagonals.get(0);
        int tmpMax = this.findMaxCompSign(diag1);
        if (tmpMax > max) {
            max = tmpMax;
            row = this.findRandomEmpty(diag1);
            column = row;
        }
        List<String> diag2 = diagonals.get(1);
        tmpMax = this.findMaxCompSign(diag2);

        if (tmpMax > max) {
            max = tmpMax;
            column = this.findRandomEmpty(diag2);
            row = this.board.getSize() - column - 1;
        }

        if (max != -1) {
            return this.board.getCellNumber(row, column);
        }
        return -1;

    }
}
