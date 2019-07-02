package ru.job4j.lsp;

public class WarehouseExtra extends StorageDecorator {
    private Storage storage;

    public WarehouseExtra(Warehouse warehouse) {
        this.storage = warehouse;
    }

    @Override
    public boolean accept(Food food) {
        return this.storage.accept(food);
    }
}
