package ru.job4j.threads.bomberman;

/**
 * @version 1.0.
 * @since 06/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class Start {
    /**
     * Метод для запуска игры.
     * Поле заполняется радномно блоками в зависимости от сложоности игры.
     * Создается поток, связанный с игроком.
     * Игрок занимает стартовую ячейку.
     * Создаются потоки-демоны, это монстры.
     * Они закончат свою работу, когда основной метод закончит свою.
     * Количество потоков определяется входящим параметром.
     * Перед запуском монстров они занимают стартовые ячейки.
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
            t.setDaemon(true);
            t.start();
        }

    }

    public static void main(String[] args) {
        new Start().init(new Board(5, 5, 1), 5);
    }
}
