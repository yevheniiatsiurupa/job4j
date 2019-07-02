package ru.job4j.lsp;


public class TrashNoRecycle extends StorageDecorator {
    private Storage storage;

    public TrashNoRecycle(Trash trash) {
        this.storage = trash;
    }

    @Override
    public boolean accept(Food food) {
        return this.storage.accept(food) && !food.isRecycle();
    }
}
