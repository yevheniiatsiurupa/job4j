package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public abstract class StorageDecorator implements Storage {
    protected List<Food> list = new ArrayList<>();


    @Override
    public List<Food> getList() {
        return this.list;
    }

    @Override
    public void add(Food food) {
        this.list.add(food);
    }

    @Override
    public List<Food> removeFood() {
        List<Food> result = this.list;
        this.list = new ArrayList<>();
        return result;
    }

}
