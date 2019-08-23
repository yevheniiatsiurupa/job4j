package ru.job4j.cinema.models;

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
}
