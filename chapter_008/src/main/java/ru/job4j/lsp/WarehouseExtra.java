package ru.job4j.lsp;

import java.util.List;

public class WarehouseExtra extends StorageDecorator {
    private Storage storage;

    public WarehouseExtra(Warehouse warehouse) {
        this.storage = warehouse;
    }

    @Override
    public boolean accept(Food food) {
        return this.storage.accept(food);
    }

    @Override
    public void add(Food food) {
        if (this.accept(food)) {
            super.add(food);
        }
    }

    @Override
    public List<Food> removeFood() {
        List<Food> result = super.removeFood();
        result.addAll(storage.removeFood());
        return result;
    }
}
