package ru.job4j.lsp;

import java.util.List;

public interface Storage {
    /**
     * Метод определяет подходит ли данный продукт этому хранилищу.
     * @param food добавляемый продукт.
     */
    boolean accept(Food food);

    /**
     * Метод возвращает список с продуктами в данном хранилище.
     */
    List<Food> getList();

    void add(Food food);
}
