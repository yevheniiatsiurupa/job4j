package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class StorageBlock {
    private List<Storage> storage = new ArrayList<>();

    /**
     * Конструктор.
     */
    public StorageBlock() {
    }

    public List<Storage> getStorage() {
        return storage;
    }

    /**
     * Метод добавляет продукт в хранилище, где accept возвращает true.
     * @param food добавляемый продукт.
     */
    public void add(Food food) {
        for (Storage tmp : storage) {
            if (tmp.accept(food)) {
                tmp.add(food);
                break;
            }
        }
    }

    public void addStorage(Storage st) {
        storage.add(st);
    }
}
