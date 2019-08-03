package ru.job4j.threads.pool.email;

/**
 * @version 1.0.
 * @since 03/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class User {
    /**
     * Имя пользователя.
     */
    private String username;

    /**
     * Адрес электронной почты пользователя.
     */
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
