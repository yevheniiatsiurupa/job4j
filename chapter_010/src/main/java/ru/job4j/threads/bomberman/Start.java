package ru.job4j.threads.bomberman;

public class Start {
    /**
     * Метод для запуска игры.
     * Создается поток, связанный с игроком.
     * @param board поле для игры.
     */
    public void init(Board board) {
        Thread player = new Player(board, 2, 2, "one");
        player.start();
    }

    public static void main(String[] args) {
        new Start().init(new Board(5, 5));
    }
}
