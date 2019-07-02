package ru.job4j.lsp;

public class StorageNormalTemp extends StorageDecorator {
    private Storage storage;

    public StorageNormalTemp(Storage store) {
        this.storage = store;
    }

    @Override
    public boolean accept(Food food) {
        return this.storage.accept(food) && !food.isColdTemp();
    }
}
