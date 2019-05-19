package ru.job4j.bank;

import java.util.Objects;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 19/05/2019
 */

public class Account {
    /**
     * Поле хранит значение количества денег.
     */
    private double values;

    /**
     * Поле хранит реквизиты счета.
     */
    private String requisites;

    public Account(double values, String requisites) {
        this.values = values;
        this.requisites = requisites;
    }

    public double getValues() {
        return values;
    }

    public String getRequisites() {
        return requisites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return requisites.equals(account.requisites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisites);
    }

    /**
     * Метод для перевода средств с одного счета на другой.
     * @param destination счет получателя.
     * @param amount сумма перевода.
     * @return возвращает true/false в случае успеха / неудачной операции.
     */
    public boolean transfer(Account destination, double amount) {
        boolean result = false;
        if (amount > 0 && amount < this.values && destination != null) {
            result = true;
            this.values -= amount;
            destination.values += amount;
        }
        return result;
    }
}
