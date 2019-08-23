package ru.job4j.cinema.models;

public class Account {
    private String name;
    private String phone;

    public Account(String name, String number) {
        this.name = name;
        this.phone = number;
    }

    public String getName() {
        return name;
    }


    public String getPhone() {
        return phone;
    }
}
