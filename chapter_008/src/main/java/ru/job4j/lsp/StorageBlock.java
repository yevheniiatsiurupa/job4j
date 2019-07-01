package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class StorageBlock {
    private List<Storage> storage = new ArrayList<>();

    /**
     * Конструктор.
     * При создании блока хранилищ создаются отдельные хранилища и добавляются в список.
     */
    public StorageBlock() {
        this.storage.add(new Warehouse());
        this.storage.add(new Shop());
        this.storage.add(new Trash());
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
                tmp.getList().add(food);
                break;
            }
        }
    }
}
