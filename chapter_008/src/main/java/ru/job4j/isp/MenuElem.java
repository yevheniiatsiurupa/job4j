package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;

public class MenuElem {
    private String name;
    private String key;
    private List<MenuElem> subElem = new ArrayList<>();

    public MenuElem(String name, String key) {
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

    public void addSub(MenuElem sub) {
        sub.setKey(this.getKey() + sub.getKey());
        this.subElem.add(sub);
    }

    public String showElem(String startLine) {
        StringBuilder sb = new StringBuilder();
        String ln = System.lineSeparator();
        sb.append(this.key);
        sb.append(this.name);
        if (!subElem.isEmpty()) {
            for (MenuElem tmp : subElem) {
                sb.append(ln);
                sb.append(startLine);
                sb.append(tmp.showElem(startLine));
            }

        }
        return sb.toString();
    }
}
