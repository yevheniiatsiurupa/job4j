package ru.job4j.stream;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 27/05/2019
 */
public class Student {
    /**
     * Поле хранит общий балл ученика по предметам.
     */
    private final int score;

    public Student(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
