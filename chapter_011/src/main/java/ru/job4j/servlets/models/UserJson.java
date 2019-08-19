package ru.job4j.servlets.models;

public class UserJson {
    private String name;
    private String surname;
    private String desc;
    private int id;

    public UserJson(String name, String surname, String desc) {
        this.name = name;
        this.surname = surname;
        this.desc = desc;
    }

    public UserJson() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
