package ru.job4j.ood;

import ru.job4j.calculator.Calculator;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 29/06/2019
 */

public class InteractCalc {
    private Calculator calculator;
    private Input input;

    /**
     * Поле хранит результат предыдущего действия.
     */
    private double calcResult;

    /**
     * Поле сообщает нужно ли переиспользовать результат предыдущего действия.
     */
    private boolean useResult = false;

    /**
     * Список содержит возможные операции калькулятора.
     */
    private List<String> operations = new ArrayList<>();

    /**
     * Список содержит пункты меню.
     */
    private List<String> menuOptions = new ArrayList<>();

    /**
     * Конструктор.
     * При создании объекта InteractCalc заполняются списки operations, menuOptions.
     * @param calculator объект Calculator.
     * @param input объект для ввода данных.
     */
    public InteractCalc(Calculator calculator, Input input) {
        this.calculator = calculator;
        this.input = input;
        operations.add("+");
        operations.add("-");
        operations.add("*");
        operations.add("/");
        menuOptions.add("c");
        menuOptions.add("n");
        menuOptions.add("q");
    }

    private String menu() {
        StringBuilder sb = new StringBuilder();
        String ln = System.lineSeparator();
        sb.append("Choose next action:");
        sb.append(ln);
        sb.append("c - continue");
        sb.append(ln);
        sb.append("n - new calculation");
        sb.append(ln);
        sb.append("q - exit");
        return sb.toString();
    }

    /**
     * Метод проверяет, что ключ содержится в списке возможных операций.
     * @param key ключ.
     * @return возвращает ключ, если он содержится в списке.
     */
    private String checkOperationInput(String key) {
        if (!operations.contains(key)) {
            throw new InvalidActionException("Invalid input");
        }
        return key;
    }

    /**
     * Метод проверяет, что ключ содержится в списке пунктов меню.
     * @param key ключ.
     * @return возвращает ключ, если он содержится в списке.
     */
    private String checkMenuInput(String key) {
        if (!menuOptions.contains(key)) {
            throw new InvalidActionException("Invalid input");
        }
        return key;
    }

    /**
     * Метод для получения операции для выполнения.
     */
    private String getOperation() {
        boolean validOperInput = false;
        String operation = null;
        do {
            try {
                String inputOper = input.ask("Choose operation: + - * /");
                operation = this.checkOperationInput(inputOper);
                validOperInput = true;
            } catch (InvalidActionException iae) {
                System.out.println("Please choose operations from list.");
            }
        } while (!validOperInput);
        return operation;
    }

    /**
     * Метод для получения пункта меню.
     */
    private String getMenuOption() {
        boolean validMenuInput = false;
        String operation = null;
        do {
            try {
                String inputMenu = input.ask(menu());
                operation = this.checkMenuInput(inputMenu);
                validMenuInput = true;
            } catch (InvalidActionException iae) {
                System.out.println("Please choose action from menu.");
            }
        } while (!validMenuInput);
        return operation;
    }

    /**
     * Метод выполняет операцию над двумя числами.
     * @param operation операция, которую необходимо выполнить.
     * @param first первое число.
     * @param second второе число.
     * @return возвращает результат действия.
     */
    private double performOperation(String operation, double first, double second) {
        double result;
        if (operation.equals("+")) {
            result = calculator.add(first, second);
        } else if (operation.equals("-")) {
            result = calculator.subtract(first, second);
        } else if (operation.equals("*")) {
            result = calculator.multiply(first, second);
        } else if (operation.equals("/")) {
            result = calculator.div(first, second);
        } else {
            throw new InvalidActionException("Invalid input");
        }
        return result;
    }

    /**
     * Метод для запуска калькулятора.
     */
    public void init() {
        boolean exit = false;
        do {
            String operation = this.getOperation();
            double first = useResult ? calcResult : Double.parseDouble(input.ask("Enter the first number"));
            double second = Double.parseDouble(input.ask("Enter the second number"));
            double result = this.performOperation(operation, first, second);
            System.out.println("Result: " + result);

            String answer = this.getMenuOption();
            if (answer.equals("c")) {
                this.calcResult = result;
                this.useResult = true;
            } else if (answer.equals("n")) {
                this.useResult = false;
            } else {
                exit = true;
            }
        } while (!exit);
    }

    public static void main(String[] args) {
        new InteractCalc(new Calculator(), new ConsoleInput()).init();
    }
}
