package ru.job4j.isp;

public class MenuElement {
    private String name;
    private String key;

    public MenuElement(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String showMenu() {
        return String.format("%s %s", this.getKey(), this.getName());
    }
}
