package ru.job4j.tdd;

public class NotEnoughKeysException extends RuntimeException {
    public NotEnoughKeysException(String msg) {
        super(msg);
    }
}
