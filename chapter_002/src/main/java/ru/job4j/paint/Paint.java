package ru.job4j.paint;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 26/04/2019
 */

public class Paint {
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

    public static void main(String[] args) {
        Paint paint = new Paint();
        paint.draw(new Triangle());
    }
}
