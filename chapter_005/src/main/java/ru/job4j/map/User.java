package ru.job4j.map;

import java.util.Calendar;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 11/06/2019
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }
}
