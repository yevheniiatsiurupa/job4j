package ru.job4j.sql;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Класс, содержащий настройки для подключения к базе SQLLite.
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 24/06/2019
 */

public class Config {
    private final Properties values = new Properties();

    /**
     * Метод загружает свойства в поле values.
     */
    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("app.properties")) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    /**
     * Метод возвращает свойство по ключу из объекта Properties.
     * @param key ключ.
     * @return возвращает свойство по ключу.
     */
    public String get(String key) {
        return this.values.getProperty(key);
    }
}
