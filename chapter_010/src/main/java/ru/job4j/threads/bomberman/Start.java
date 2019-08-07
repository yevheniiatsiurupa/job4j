package ru.job4j.threads.bomberman;

/**
 * @version 1.0.
 * @since 06/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class Start {
    /**
     * Метод для запуска игры.
     * Создается поток, связанный с игроком.
     * @param board поле для игры.
     */
    public void init(Board board, int monsterNumber) {
        board.setBlocks();
        Cell bomberStart = board.getRandomFree();
        BomberMan bomberman = new BomberMan(board, bomberStart.getRow(), bomberStart.getCol(), "bomberman");
        bomberman.start();
        for (int i = 0; i < monsterNumber; i++) {
            Cell start = board.getRandomFree();
            Thread t = new Monster(board, start.getRow(), start.getCol(), "monster " + i, bomberman);
            t.start();
        }

    }

    public static void main(String[] args) {
        new Start().init(new Board(5, 5, 1), 3);
    }
}
