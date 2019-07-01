package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
    private List<Food> list = new ArrayList<>();

    public List<Food> getList() {
        return list;
    }

    @Override
    public void add(Food food) {
        this.list.add(food);
    }
}
