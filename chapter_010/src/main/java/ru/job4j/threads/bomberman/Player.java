package ru.job4j.threads.bomberman;

public class Player extends Thread {
    /**
     * Поле хранит ссылку на игровое поле.
     */
    private final Board board;

    /**
     * Поле хранит ссылку на текущую ячейку для игрока.
     */
    private Cell currCell;

    /**
     * Поле хранит имя игрока.
     */
    private String name;

    public Player(Board board, int rowStart, int colStart, String name) {
        this.board = board;
        this.name = name;
        this.currCell = board.getCell(rowStart, colStart);
    }

    /**
     * Основной метод движения игрока.
     * Игрок занимает стартовую ячейку.
     * Пока поток не прерван, то с интервалом в 1 секунду
     * игрок перемещается по полю.
     * Игрок пытается занять ячейку с помощью метода move.
     * Если не получается, то через 0.5 сек пробует опять занять другую ячейку
     * и так пока не сделает ход.
     */
    @Override
    public void run() {
        this.board.startMove(currCell);
        System.out.println(String.format("Player %s starts at %s", this.name, this.currCell));
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
                boolean moved = false;
                Cell dest = null;
                while (!moved) {
                    dest = this.board.getRandomDest(currCell);
                    moved = this.board.move(this.currCell, dest);
                    Thread.sleep(500);
                }
                this.currCell = dest;
                System.out.println(String.format("Player %s moved to %s", this.name, this.currCell));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
