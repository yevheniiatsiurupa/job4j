package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

/**
 * @version 1.0.
 * @since 21/04/2019.
 */
public class Tracker implements ITracker {
    /**
     * Массив для хранения заявок.
     */
    private final List<Item> items = new ArrayList<>();


    /**
     * Случайное число.
     */
    private static final Random RN = new Random();

    /**
     * Метод, реализующий добавление заявки в хранилище.
     * @param item новая заявка.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * @return уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Метод для редактирования заявок.
     * @param id номер id заявки, которую требуется заменить.
     * @param item заявка, которая заменяет предыдущую.
     * @return возвращает true, если замена прошла успешно.
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        for (int i = 0; i != items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                item.setId(items.get(i).getId());
                items.set(i, item);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод для поиска элемента по ID.
     * @param id номер id искомой заявки.
     * @return возвращает искомую заявку.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item tmp :items) {
            if (tmp != null && tmp.getId().equals(id)) {
                result = tmp;
                break;
            }
        }
        return result;
    }

    /**
     * Метод для удаления заявок.
     * Удаляет в хранилище заявку по ее id, производит сдвиг элементов в массиве хранилища.
     * @param id номер id заявки, которую требуется удалить.
     * @return возвращает true, если удаление прошло успешно.
     */
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i != items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                items.remove(i);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод для получения всех заявок.
     * @return возвращает массив со всеми заявками (все ненулевые элементы хранилища).
     */
    public List<Item> findAll() {
        return items;
    }

    /**
     * Метод для получения списка по имени.
     * @param key имя заявки для поиска.
     * @return возвращает массив с заявками, имя которых совпадает с key.
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        for (Item tmp :items) {
            if (tmp.getName().equals(key)) {
                result.add(tmp);
            }
        }
        return result;
    }
}
