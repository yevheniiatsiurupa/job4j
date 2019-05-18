package ru.job4j.map;

/**
 * @author Evgeniya Tsiurupa
 * @version 2.0
 * @since 18/05/2019
 */

public class User implements Comparable<User> {
    private int id;
    private String name;
    private String city;
    private Integer age;

    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Метод реализует аналогичный метод из интерфейса Comparable.
     * Метод реализует сравнение User сначала по возрасту, потом по имени.
     */
    public int compareTo(User o) {
        final int rsl = this.age.compareTo(o.age);
        return rsl != 0 ? rsl : this.name.compareTo(o.name);
    }
}
