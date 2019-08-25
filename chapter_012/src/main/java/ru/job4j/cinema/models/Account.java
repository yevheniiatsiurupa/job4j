package ru.job4j.cinema.models;

import java.util.Objects;

/**
 * Класс пользователя с именем и номером телефона.
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 25/08/2019
 */

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(phone, account.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }
}
