package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 16/06/2019
 */

public class Config {
    /**
     * Путь к файлу конфигурации.
     * Хранит настройки в формате ключ - значение.
     * Ключ и значение разделены знаком "=".
     */
    private final String path;

    /**
     * Поле хранит ключи и значения из файла конфигурации.
     */
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод считывает все ключи и значения в карту values.
     */
    public void load() {
        List<String> temp = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            temp = br.lines().filter(x -> x.contains("=")).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String tmp : temp) {
            String[] parts = tmp.split("=");
            values.put(parts[0], parts[1]);
        }
    }

    /**
     * Метод возвращает значение по ключу.
     * @param key ключ.
     * @return возвращает значение.
     */
    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new Config("app.properties"));
    }
}
