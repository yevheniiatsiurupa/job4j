package ru.job4j.lsp;

public class Vegetables extends Food {
    public Vegetables(String name, long expireDate, long createDate, int price, int possibleDiscount,  boolean recycle) {
        super(name, expireDate, createDate, price, possibleDiscount, recycle, true);
    }
}
