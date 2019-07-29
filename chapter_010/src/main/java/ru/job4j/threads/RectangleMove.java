package ru.job4j.threads;

import javafx.scene.shape.Rectangle;

import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0.
 * @since 29/07/2019.
 * @author Evgeniya Tsiurupa
 */

public class RectangleMove implements Runnable {
    /**
     * Поле хранит фигуру прямоугольник, который будет менять свое положение на поле.
     */
    private final Rectangle rect;

    /**
     * Размер поля Х, по которому перемещается прямоугольник.
     */
    private int limitX;

    /**
     * Размер поля У, по которому перемещается прямоугольник.
     */
    private int limitY;

    /**
     * Поле хранит список возможных перемещений (delta): 0, 1, -1.
     */
    private List<Integer> rand;

    public RectangleMove(Rectangle rect, int limitX, int limitY) {
        this.rect = rect;
        this.limitX = limitX;
        this.limitY = limitY;
        this.rand = Arrays.asList(1, 0, -1);
    }

    /**
     * Метод для запуска движения прямоугольника по полю.
     * Начальное движение задано объектом currDir.
     * В каждой итерации проверяется находится ли прямоугольник внутри поля.
     * Если нет (если он на краю или в углу), то происходит смена направления (метод getDirection).
     * Координаты х и у прямоугольника изменяются в зависимости от объекта currDir,
     * который хранит необходимые смещения фигуры.
     * После изменения координат поток исполнения приостанавливается на 60 мс.
     */
    @Override
    public void run() {
        Direction currDir = new Direction(1, 0);

        while (true) {
            if (!this.checkPosition()) {
                currDir = this.getDirection();
            }
            this.rect.setX(this.rect.getX() + currDir.getDeltaX());
            this.rect.setY(this.rect.getY() + currDir.getDeltaY());

            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод проверяет, что прямоугольник находится внутри поля (не в углу, не на краю).
     */
    private boolean checkPosition() {
        boolean centralPosition = true;
        if (rect.getX() == 0
                || rect.getX() == limitX - this.rect.getWidth()
                || rect.getY() == 0
                || rect.getY() == limitY - this.rect.getHeight()) {
            centralPosition = false;
        }
        return centralPosition;
    }

    /**
     * Метод для получения нового направления движения прямоугольника, который достиг крайнего положения (угол, край).
     * Проверяется сначала, где находится прямоугольник.
     * Если находится в углах, то направление движения сразу определено по диагонали вовнутрь поля.
     * Если находится на краях, то направление выбирается рандомно из трех доступных направлений
     * (две диагонали вовнутрь поля, перпендикулярно краю).
     * @return возвращает объект Direction, в котором указано будущее направление движения (смещение х, смещение у).
     */
    private Direction getDirection() {
        Direction dir;
        if (rect.getX() == 0 && rect.getY() == 0) {
            dir = new Direction(1, 1);
        } else if (rect.getX() == limitX - this.rect.getWidth() && rect.getY() == 0) {
            dir = new Direction(-1, 1);
        } else if (rect.getX() == 0 && rect.getY() == limitY - this.rect.getHeight()) {
            dir = new Direction(1, -1);
        } else if (rect.getX() == limitX - this.rect.getWidth() && rect.getY() == limitY - this.rect.getHeight()) {
            dir = new Direction(-1, -1);
        } else if (rect.getY() == 0) {
            int random = (int) (Math.random() * this.rand.size());
            dir = new Direction(this.rand.get(random), 1);
        } else if (rect.getX() == 0) {
            int random = (int) (Math.random() * this.rand.size());
            dir = new Direction(1, this.rand.get(random));
        } else if (rect.getX() == limitX - this.rect.getWidth()) {
            int random = (int) (Math.random() * this.rand.size());
            dir = new Direction(-1, this.rand.get(random));
        } else {
            int random = (int) (Math.random() * this.rand.size());
            dir = new Direction(this.rand.get(random), -1);
        }
        return dir;
    }

    /**
     * Внутренний статический класс.
     * Определяет направление движения, хранит значения смещения по х и по у.
     */
    public static class Direction {
        private int deltaX;
        private int deltaY;

        public Direction(int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }

        public int getDeltaX() {
            return deltaX;
        }

        public int getDeltaY() {
            return deltaY;
        }
    }
}