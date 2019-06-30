package ru.job4j.calculator;
/**
 * Calculator.
 * Производит простейшие арифметические операции.
 * @author Evgeniya Tsiurupa
 */
public class Calculator {
    /**
     * Method add.
     * @param first Enter the first number here.
     * @param second Enter the second number here.
     * @return Result of addition.
     */
    public double add(double first, double second) {
        return first + second;
    }

    /**
     * Method subtract.
     * @param first Enter the first number here.
     * @param second Enter the second number here.
     * @return Result of subtraction.
     */
    public double subtract(double first, double second) {
        return first - second;
    }

    /**
     * Method div.
     * @param first Enter the first number here.
     * @param second Enter the second number here.
     * @return Result of division.
     */
    public double div(double first, double second) {
        return first / second;
    }

    /**
     * Method multiply.
     * @param first Enter the first number here.
     * @param second Enter the second number here.
     * @return Result of multiplication.
     */
    public double multiply(double first, double second) {
        return first * second;
    }

    /**
     * Method sin.
     * @param first Enter the first number here.
     * @return sine of the number.
     */
    public double sin(double first) {
        return Math.sin(first);
    }

    /**
     * Method cos.
     * @param first Enter the first number here.
     * @return cosine of the number.
     */
    public double cos(double first) {
        return Math.cos(first);
    }

    /**
     * Method square root.
     * @param first Enter the first number here.
     * @return square root of the number.
     */
    public double sqrt(double first) {
        return Math.sqrt(first);
    }
}