package ru.job4j.tracker;

import java.util.Random;
import java.util.Arrays;

/**
 * @version 1.0.
 * @since 21/04/2019.
 */
public class Tracker {
    /**
     * Массив для хранения заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

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
        this.items[this.position++] = item;
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
        for (int i = 0; i != this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                this.items[i] = item;
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
        for (int i = 0; i < this.position; i++) {
            if (this.items[i] != null && this.items[i].getId().equals(id)) {
                result = this.items[i];
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
        for (int i = 0; i != this.items.length; i++) {
            if (this.items[i].getId().equals(id)) {
                System.arraycopy(this.items, i + 1, this.items, i, this.position - i - 1);
                items[position - 1] = null;
                position--;
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
    public Item[] findAll() {
        return Arrays.copyOf(this.items, this.position);
    }

    /**
     * Метод для получения списка по имени.
     * @param key имя заявки для поиска.
     * @return возвращает массив с заявками, имя которых совпадает с key.
     */
    public Item[] findByName(String key) {
        int count = 0;
        Item[] tmp = new Item[this.items.length];
        for (int i = 0; i < position; i++) {
            if (items[i].getName().equals(key)) {
                tmp[count] = items[i];
                count++;
            }
        }
        return Arrays.copyOf(tmp, count);
    }
}
