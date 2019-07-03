package ru.job4j.lsp;


import java.util.ArrayList;
import java.util.List;

public class TrashRecycle extends StorageDecorator {
    private Storage storage;

    public TrashRecycle(Trash trash) {
        this.storage = trash;
    }

    @Override
    public boolean accept(Food food) {
        return this.storage.accept(food);
    }

    @Override
    public void add(Food food) {
        if (food.isRecycle()) {
            super.add(food);
        } else {
            this.storage.add(food);
        }
    }

    @Override
    public List<Food> removeFood() {
        List<Food> result = super.removeFood();
        result.addAll(storage.removeFood());
        return result;
    }
}
