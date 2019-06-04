package job4j.tictactoe;

import javafx.scene.shape.Rectangle;

public class Figure3T extends Rectangle {
    /**
     * Хранит информацию о клетке.
     * Клетка на поле может содержать Х, О или быть пустой.
     */
    private boolean markX = false;
    private boolean markO = false;

    public Figure3T() {
    }

    public Figure3T(boolean markX, boolean markO) {
        this.markX = markX;
        this.markO = markO;
    }

    public void take(boolean markX) {
            this.markX = markX;
            this.markO = !markX;
    }

    /**
     * Метод возвращает true / false занята ли клетка крестиком.
     * @return возвращает true / false.
     */
    public boolean hasMarkX() {
        return this.markX;
    }

    /**
     * Метод возвращает true / false занята ли клетка ноликом.
     * @return возвращает true / false.
     */
    public boolean hasMarkO() {
        return this.markO;
    }
}
