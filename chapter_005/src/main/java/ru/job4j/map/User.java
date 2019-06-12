package ru.job4j.map;

import java.util.*;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Map<User, Object> testMap = new HashMap<>();
        Calendar firstDate = new GregorianCalendar(1990, 2, 10);
        Calendar secondDate = new GregorianCalendar(1990, 2, 10);
        User first = new User("John", 2, firstDate);
        User second = new User("John", 2, secondDate);
        testMap.put(first, "first");
        testMap.put(second, "second");
        System.out.println(testMap);
    }
}
