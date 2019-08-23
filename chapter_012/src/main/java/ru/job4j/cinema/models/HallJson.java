package ru.job4j.cinema.models;

import java.util.List;

public class HallJson {
    private List<HallPlace> seats;
    private int rows;
    private int places;

    public HallJson(List<HallPlace> seats, int rows, int places) {
        this.seats = seats;
        this.rows = rows;
        this.places = places;
    }

    public HallJson() {
    }

    public List<HallPlace> getSeats() {
        return seats;
    }

    public void setSeats(List<HallPlace> seats) {
        this.seats = seats;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }
}
