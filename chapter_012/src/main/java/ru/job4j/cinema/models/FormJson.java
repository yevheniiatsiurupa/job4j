package ru.job4j.cinema.models;

/**
 * Класс представляет форму json, полученную с страницы.
 * Форма содержит имя пользователя, номер телефона, номер места.
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 25/08/2019
 */
public class FormJson {
    private String username;
    private String phone;
    private String id;

    public FormJson(String username, String phone, String id) {
        this.username = username;
        this.phone = phone;
        this.id = id;
    }

    public FormJson() {
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getId() {
        return id;
    }
}
