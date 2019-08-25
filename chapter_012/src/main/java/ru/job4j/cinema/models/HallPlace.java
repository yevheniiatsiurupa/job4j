package ru.job4j.cinema.models;

import java.util.Objects;

/**
 * Класс представляет место в зале.
 * Место имеет номер места, ряда, номер Id, цену и статус.
 * Статус по умолчанию - free.
 * Id места в форме "ряд-место", например, "2-3".
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 25/08/2019
 */
public class HallPlace {
    private int row;
    private int place;
    private int price;
    private String status = "free";
    private String id;


    public HallPlace(int row, int place, int price) {
        this.row = row;
        this.place = place;
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public int getPlace() {
        return place;
    }

    public int getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HallPlace hallPlace = (HallPlace) o;
        return Objects.equals(id, hallPlace.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
