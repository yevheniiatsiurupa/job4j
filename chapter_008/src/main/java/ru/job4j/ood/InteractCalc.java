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
    private Input input;
    private Operations operations;

    /**
     * Поле хранит результат предыдущего действия.
     */
    private double calcResult;

    /**
     * Поле сообщает нужно ли переиспользовать результат предыдущего действия.
     */
    private boolean useResult = false;


    /**
     * Список содержит пункты меню.
     */
    private List<String> menuOptions = new ArrayList<>();

    /**
     * Конструктор.
     * При создании объекта InteractCalc заполняются списки operations, menuOptions.
     * @param input объект для ввода данных.
     */
    public InteractCalc(Input input, Operations operations) {
        this.input = input;
        this.operations = operations;
        this.operations.fillOper();
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
                String inputOper = input.ask("Choose operation:" + operations.showKeys());
                operation = operations.checkOperationInput(inputOper);
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
     * Метод для получения списка аргументов.
     * @param key ключ операции.
     * @return возвращает список аргументов нужной длины для операции.
     */
    private List<Double> getArgs(String key) {
        List<Double> result = new ArrayList<>();
        int argNumb = operations.getArgsNumbByKey(key);
        double first = useResult ? calcResult : Double.parseDouble(input.ask("Enter the first number"));
        result.add(first);
        for (int i = 1; i < argNumb; i++) {
            double next = Double.parseDouble(input.ask("Enter the second number"));
            result.add(next);
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
            List<Double> args = this.getArgs(operation);
            double result = this.operations.performOper(operation, args);
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
        new InteractCalc(new ConsoleInput(), new EngOperations(new Calculator())).init();
    }
}
