package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class Warehouse  implements  Storage {
    private List<Food> list = new ArrayList<>();

    @Override
    public List<Food> getList() {
        return list;
    }

    @Override
    public boolean accept(Food food) {
        return food.checkDate(System.currentTimeMillis()) < 25;
    }

    @Override
    public void add(Food food) {
        list.add(food);
    }

    @Override
    public List<Food> removeFood() {
        List<Food> result = this.list;
        this.list = new ArrayList<>();
        return result;
    }
}
