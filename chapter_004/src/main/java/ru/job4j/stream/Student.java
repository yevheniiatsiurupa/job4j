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
    /**
     * Поле хранит фамилию ученика.
     */
    private final String surname;

    public Student(int score, String surname) {
        this.score = score;
        this.surname = surname;
    }

    public int getScore() {
        return score;
    }

    public String getSurname() {
        return surname;
    }
}
