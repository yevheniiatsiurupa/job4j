package ru.job4j.search;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 12/05/2019
 */

public class Person {
    /**
     * Поля хранят информацию о человеке.
     */
    private String name;
    private String surname;
    private String phone;
    private String address;

    public Person(String name, String surname, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getAddress() {
        return this.address;
    }
}
