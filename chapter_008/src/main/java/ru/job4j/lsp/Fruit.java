package ru.job4j.lsp;

public class Fruit extends Food {
    public Fruit(String name, long expireDate, long createDate, int price, int possibleDiscount,  boolean recycle) {
        super(name, expireDate, createDate, price, possibleDiscount, recycle, false);
    }
}
