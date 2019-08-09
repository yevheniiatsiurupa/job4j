package ru.job4j.servlets;

/**
 * @version 1.0.
 * @since 08/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class User {
    private int id;
    private String name;
    private String login;
    private String email;
    private long createDate;

    public User(String name) {
        this.name = name;
    }

    public User(String name, String login, String email, long createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
