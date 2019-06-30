package ru.job4j.ood;

import ru.job4j.calculator.Calculator;

import java.util.*;

public class Operations {
    /**
     * Поле хранит ссылку на объект калькулятора.
     */
    protected Calculator calculator;

    /**
     * Карта хранит ключи операций и объекты соответствующие операциям.
     */
    protected Map<String, BasicOperation> operationMap = new LinkedHashMap<>();

    /**
     * Конструктор.
     */
    public Operations(Calculator calculator) {
        this.calculator = calculator;
    }

    /**
     * Метод заполняет карту ключами и действиями.
     */
    public void fillOper() {
        BasicOperation add = new Add();
        this.operationMap.put(add.key(), add);
        BasicOperation subtract = new Subtract();
        this.operationMap.put(subtract.key(), subtract);
        BasicOperation multiply = new Multiply();
        this.operationMap.put(multiply.key(), multiply);
        BasicOperation div = new Divide();
        this.operationMap.put(div.key(), div);
    }

    /**
     * Метод проверяет, что ключ содержится в списке возможных операций.
     * @param key ключ.
     * @return возвращает ключ, если он содержится в списке.
     */
    public String checkOperationInput(String key) {
        if (!operationMap.keySet().contains(key)) {
            throw new InvalidActionException("Invalid input");
        }
        return key;
    }

    /**
     * Метод для выполнения операции по ключу и входящим параметрам.
     * @param key ключ операции.
     * @param args список аргументов для операции.
     * @return возвращает результат операции.
     */
    public double performOper(String key, List<Double> args) {
        return this.operationMap.get(key).perform(args);
    }

    /**
     * Метод для получения количества аргументов по ключу.
     * @param key ключ операции.
     * @return возвращает количество необходимых аргументов.
     */
    public int getArgsNumbByKey(String key) {
        return this.operationMap.get(key).getArgsNumb();
    }

    /**
     * Метод возвращает строку со всеми ключами.
     */
    public String showKeys() {
        StringBuilder sb = new StringBuilder();
        for (String tmp : this.operationMap.keySet()) {
            sb.append(" ");
            sb.append(tmp);
        }
        return sb.toString();
    }

    //Внутренние классы действий.

    public class Add implements BasicOperation {

        @Override
        public String key() {
            return "+";
        }

        @Override
        public double perform(List<Double> numbers) {
            return calculator.add(numbers.get(0), numbers.get(1));
        }

        @Override
        public int getArgsNumb() {
            return 2;
        }
    }

    public class Subtract implements BasicOperation {

        @Override
        public String key() {
            return "-";
        }

        @Override
        public double perform(List<Double> numbers) {
            return calculator.subtract(numbers.get(0), numbers.get(1));
        }

        @Override
        public int getArgsNumb() {
            return 2;
        }
    }

    public class Multiply implements BasicOperation {

        @Override
        public String key() {
            return "*";
        }

        @Override
        public double perform(List<Double> numbers) {
            return calculator.multiply(numbers.get(0), numbers.get(1));
        }

        @Override
        public int getArgsNumb() {
            return 2;
        }
    }

    public class Divide implements BasicOperation {

        @Override
        public String key() {
            return "/";
        }

        @Override
        public double perform(List<Double> numbers) {
            return calculator.div(numbers.get(0), numbers.get(1));
        }

        @Override
        public int getArgsNumb() {
            return 2;
        }
    }

}
