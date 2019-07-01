package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Storage {
    private List<Food> list = new ArrayList<>();

    @Override
    public List<Food> getList() {
        return list;
    }

    @Override
    public boolean accept(Food food) {
        return food.checkDate(System.currentTimeMillis()) > 100;
    }

    public void add(Food food) {
        list.add(food);
    }
}
