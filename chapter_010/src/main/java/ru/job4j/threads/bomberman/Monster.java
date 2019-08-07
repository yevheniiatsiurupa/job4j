package ru.job4j.threads.bomberman;

import java.util.List;

/**
 * @version 1.0.
 * @since 06/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class Monster extends Thread {
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

    /**
     * Поле хранит ссылку на бомбермена.
     */
    private BomberMan bomberman;

    public Monster(Board board, int rowStart, int colStart, String name, BomberMan bomberman) {
        this.board = board;
        this.name = name;
        this.bomberman = bomberman;
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
        while (this.board.hasNoWinner()) {
            try {
                Thread.sleep(1000);
                boolean moved = false;
                Cell dest = null;
                while (!moved) {
                    dest = this.getDestCell();
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

    public Cell getDestCell() {
        return this.board.getRandomDest(currCell);
    }
}
