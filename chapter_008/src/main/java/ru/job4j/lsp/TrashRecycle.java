package ru.job4j.lsp;


public class TrashRecycle extends StorageDecorator {
    private Storage storage;

    public TrashRecycle(Trash trash) {
        this.storage = trash;
    }

    @Override
    public boolean accept(Food food) {
        return this.storage.accept(food) && food.isRecycle();
    }
}
