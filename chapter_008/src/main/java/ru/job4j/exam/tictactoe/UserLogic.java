package ru.job4j.exam.tictactoe;

public class UserLogic {
    /**
     * Ссылка на игровое поле.
     */
    private Board board;

    /**
     * Ссылка на объект для ввода данных.
     */
    private ConsoleInput input;

    /**
     * Поле хранит знак пользователя (крестик / нолик).
     */
    private String userSign;

    /**
     * Конструктор.
     */
    public UserLogic(Board board, ConsoleInput input) {
        this.board = board;
        this.input = input;
    }

    public String getUserSign() {
        return userSign;
    }


    /**
     * Метод для выбора пользователем знака (крестик / нолик).
     * Присваивает компьютеру оставшийся знак.
     */
    public void chooseSign() {
        String answer = input.ask("Choose your sign X or O.");
        if (!answer.equals("X") && !answer.equals("O")) {
            throw new InvalidInputException("Sign should be X or O.");
        }
        this.userSign = answer;
    }

    /**
     * Метод возвращает знак для компьютера (Х / О) в зависимости от знака пользователя.
     */
    public String getCompSign() {
        return "X".equals(userSign) ? "O" : "X";
    }

    /**
     * Метод проверяет, что число находится в пределах поля.
     */
    private int checkNumber(int number) {
        if (number < 0 || number > this.board.getSize() - 1) {
            throw new InvalidInputException("Out of bounds.");
        }
        return number;
    }

    /**
     * Метод для получения числа (координаты поля).
     */
    private int getNumber(String question) {
        int number = -1;
        boolean validNumb = false;
        while (!validNumb) {
            try {
                int tmpNumber = Integer.parseInt(input.ask(question));
                number = this.checkNumber(tmpNumber);
                validNumb = true;
            } catch (InvalidInputException iie) {
                System.out.println(String.format("Choose number from 0 to %d", this.board.getSize() - 1));
            } catch (NumberFormatException nfe) {
                System.out.println("Please, enter the number.");
            }
        }
        return number;
    }

    /**
     * Метод запрашивает у пользователя ход (строка, колонка)
     * и заносит его на поле.
     */
    public void drawUserSign() {
        int row = this.getNumber("Enter the row number");
        int column = this.getNumber("Enter the column number");
        int cell = this.board.getCellNumber(row, column);
        if (!this.board.emptyCell.contains(cell)) {
            throw new InvalidInputException("This cell is occupied. Choose another cell.");
        }
        this.board.drawSign(row, column, this.userSign);
    }
}
