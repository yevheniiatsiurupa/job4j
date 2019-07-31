package ru.job4j.threads;

/**
 * @version 1.0.
 * @since 31/07/2019.
 * @author Evgeniya Tsiurupa
 */

public class User {
    private int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
