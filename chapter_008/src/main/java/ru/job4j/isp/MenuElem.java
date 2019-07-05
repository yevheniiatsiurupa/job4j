package ru.job4j.isp;

public class MenuElem {
    private String name;
    private String key;

    public MenuElem(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String showElem() {
        return String.format("%s%s", this.key, this.name);
    }
}
