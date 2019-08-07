package ru.job4j.threads.bomberman;

/**
 * @version 1.0.
 * @since 06/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class BomberMan extends Thread {
    /**
     * Поле хранит ссылку на игровое поле.
     */
    protected final Board board;

    /**
     * Поле хранит ссылку на текущую ячейку для игрока.
     */
    protected Cell currCell;

    /**
     * Поле хранит имя игрока.
     */
    private String name;

    public BomberMan(Board board, int rowStart, int colStart, String name) {
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
                while (!moved && !Thread.currentThread().isInterrupted()) {
                    dest = this.getDestCell();
                    moved = this.board.move(this.currCell, dest);
                    Thread.sleep(500);
                }
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }
                this.currCell = dest;
                System.out.println(String.format("Player %s moved to %s", this.name, this.currCell));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Метод для получения следующего хода.
     * Должен быть объединен с пользовательским вводом.
     */
    public Cell getDestCell() {
        return this.board.getRandomDest(currCell);
    }
}
