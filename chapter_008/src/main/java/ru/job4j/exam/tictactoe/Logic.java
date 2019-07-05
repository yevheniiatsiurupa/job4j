package ru.job4j.exam.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Logic {
    private Board board;
    private String compSign;
    private String userSign;
    private int winNumb;
    private int userWins = 0;
    private int compWins = 0;

    public Logic(Board board, UserLogic user, int winNumb) {
        this.board = board;
        compSign = user.getCompSign();
        userSign = user.getUserSign();
        this.winNumb = winNumb;
    }

    /**
     * Метод проверяет есть ли победитель в игре, нужно ли ее закончить.
     * Если победителя нет и нет свободных клеток, то результат - ничья.
     */
    public boolean hasWinner() {
        boolean result = false;
        String winner = this.findWinnerSign();
        if (compSign.equals(winner)) {
            System.out.println("Sorry, you've lost this game.");
            compWins++;
            userWins = 0;
            result = true;
        } else if (userSign.equals(winner)) {
            System.out.println("Congratulations!! You are the winner of this game!");
            userWins++;
            compWins = 0;
            result = true;
        } else {
            if (this.hasAllOccupied()) {
                System.out.println("It's a draw in this game!");
                userWins = 0;
                compWins = 0;
                result = true;
            }
        }
        return result;
    }

    public boolean hasOverallWinner() {
        if (userWins == winNumb) {
            System.out.println("Congratulations!! You are the overall winner!");
            return true;
        }
        if (compWins == winNumb) {
            System.out.println("Computer is the overall winner!");
            return true;
        }
        return false;
    }

    /**
     * Метод проверяет, что все клетки поля заняты.
     */
    private boolean hasAllOccupied() {
        return this.board.emptyCell.isEmpty();
    }

    /**
     * Метод перебирает все линии поля и ищет знак победителя.
     * @return возвращает знак победителя или ноль.
     */
    private String findWinnerSign() {
        List<List<String>> allLines = new ArrayList<>(this.board.getRowsList());
        allLines.addAll(this.board.getColumnsList());
        allLines.addAll(this.board.getDiagonalsList());
        for (List<String> tmp : allLines) {
            String first = tmp.get(0);
            if (first.equals(compSign) || first.equals(userSign)) {
                boolean win = true;
                for (String cell : tmp) {
                    if (!cell.equals(first)) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return first;
                }
            }
        }
        return null;
    }
}
