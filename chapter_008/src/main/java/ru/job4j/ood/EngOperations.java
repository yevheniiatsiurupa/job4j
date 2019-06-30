package ru.job4j.ood;


import ru.job4j.calculator.Calculator;

import java.util.List;

public class EngOperations extends Operations {

    public EngOperations(Calculator calculator) {
        super(calculator);
    }

    /**
     * Метод переопределяет аналогичный метод класса Operations.
     * Вызывает родительский метод, добавляет в карту новые объекты.
     */
    @Override
    public void fillOper() {
        super.fillOper();
        BasicOperation sin = new Sine();
        this.operationMap.put(sin.key(), sin);
        BasicOperation cos = new Cosine();
        this.operationMap.put(cos.key(), cos);
        BasicOperation sq = new SquareRoot();
        this.operationMap.put(sq.key(), sq);
    }

    //Внутренние классы действий.

    public class Sine implements BasicOperation {

        @Override
        public String key() {
            return "sin";
        }

        @Override
        public double perform(List<Double> numbers) {
            return calculator.sin(numbers.get(0));
        }

        @Override
        public int getArgsNumb() {
            return 1;
        }
    }

    public class Cosine implements BasicOperation {

        @Override
        public String key() {
            return "cos";
        }

        @Override
        public double perform(List<Double> numbers) {
            return calculator.cos(numbers.get(0));
        }

        @Override
        public int getArgsNumb() {
            return 1;
        }
    }

    public class SquareRoot implements BasicOperation {

        @Override
        public String key() {
            return "sqr";
        }

        @Override
        public double perform(List<Double> numbers) {
            return calculator.sqrt(numbers.get(0));
        }

        @Override
        public int getArgsNumb() {
            return 1;
        }
    }
}
