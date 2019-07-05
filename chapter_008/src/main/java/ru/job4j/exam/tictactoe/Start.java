package ru.job4j.exam.tictactoe;

public class Start {
    private ConsoleInput input;

    public Start(ConsoleInput input) {
        this.input = input;
    }

    /**
     * Метод для проверки размера поля.
     */
    private int checkSize(int size) {
        if (size < 3) {
            throw new InvalidInputException("Size should be not less than 3.");
        }
        return size;
    }

    /**
     * Метод для получения размера поля.
     * @return размер поля.
     */
    private int getBoardSize() {
        boolean validSize = false;
        int boardSize = 0;
        while (!validSize) {
            try {
                int tmpSize = Integer.parseInt(input.ask("Please enter the board size:"));
                boardSize = this.checkSize(tmpSize);
                validSize = true;
            } catch (InvalidInputException iie) {
                System.out.println("Size should be 3 or greater.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter the number.");
            }
        }
        return boardSize;
    }

    /**
     * Метод узнает, какой фигурой будет ходить пользователь.
     */
    private void getUserSign(UserLogic user) {
        boolean validSign = false;
        while (!validSign) {
            try {
                user.chooseSign();
                validSign = true;
            } catch (InvalidInputException iie) {
                System.out.println("Sign should be X or O.");
            }
        }
    }

    /**
     * Метод хода пользователя.
     */
    private void getUserMove(UserLogic user, Board board) {
        boolean validCell = false;
        while (!validCell) {
            try {
                user.drawUserSign();
                validCell = true;
            } catch (InvalidInputException iie) {
                System.out.println("This cell is occupied. Choose another cell.");
            }

        }
        System.out.println("Your move:");
        board.showBoard();
    }

    /**
     * Метод хода компьютера.
     */
    private void getCompMove(ComputerLogic comp, Board board) {
        comp.drawCompSign();
        System.out.println("Computer's move:");
        board.showBoard();
    }

    private int getLevel() {
        boolean validLevel = false;
        int winNumb = 1;
        while (!validLevel) {
            try {
                String level = input.ask("Choose level: normal (n) / difficult (d)");
                if (!level.equals("d") && !level.equals("n")) {
                    throw new InvalidInputException("Wrong input.");
                }
                validLevel = true;
                if (level.equals("d")) {
                    winNumb = 2;
                }
            } catch (InvalidInputException iie) {
                System.out.println("Enter n or d");
            }
        }
        return winNumb;
    }

    /**
     * Основной метод программы.
     */
    public void init() {
        System.out.println("Welcome to Tic Tac Toe!!!\n");
        int boardSize = this.getBoardSize();
        Board board = new Board(boardSize, "-");

        UserLogic user = new UserLogic(board, input);
        System.out.println("Note that X sign has the first move.");
        this.getUserSign(user);
        int winNumb = this.getLevel();

        System.out.println("Note: row and column numbers start from 0.");
        ComputerLogic comp = new ComputerLogic(board, user);
        Logic logic = new Logic(board, user, winNumb);

        boolean hasOverAllWinner = false;
        while (!hasOverAllWinner) {
            System.out.println("New game:");
            board.fillBoard();
            board.showBoard();
            if (user.getUserSign().equals("O")) {
                comp.drawCompSign();
                board.showBoard();
            }
            boolean hasWinner = false;
            while (!hasWinner) {
                this.getUserMove(user, board);
                hasWinner = logic.hasWinner();
                if (!hasWinner) {
                    this.getCompMove(comp, board);
                    hasWinner = logic.hasWinner();
                }
                if (hasWinner) {
                    hasOverAllWinner = logic.hasOverallWinner();
                }
            }
        }

    }

    public static void main(String[] args) {
        new Start(new ConsoleInput()).init();
    }
}
