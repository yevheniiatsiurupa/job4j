package ru.job4j.stream;

import java.util.Objects;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 27/05/2019
 */

public class Address {
    /**
     * Поля хранят адрес клиента.
     */
    private String city;
    private String street;
    private int home;
    private int apartment;

    public Address(String city, String street, int home, int apartment) {
        this.city = city;
        this.street = street;
        this.home = home;
        this.apartment = apartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return home == address.home
                && apartment == address.apartment
                && Objects.equals(city, address.city)
                && Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, home, apartment);
    }

    public String getCity() {
        return city;
    }
}
