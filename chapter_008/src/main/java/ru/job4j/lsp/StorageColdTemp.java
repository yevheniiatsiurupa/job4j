package ru.job4j.lsp;

public class StorageColdTemp  extends StorageDecorator {
    private Storage storage;

    public StorageColdTemp(Storage store) {
        this.storage = store;
    }

    @Override
    public boolean accept(Food food) {
        return this.storage.accept(food) && food.isColdTemp();
    }
}
