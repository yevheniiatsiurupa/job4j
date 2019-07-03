package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
    private List<Food> list = new ArrayList<>();

    @Override
    public List<Food> getList() {
        return list;
    }

    @Override
    public boolean accept(Food food) {
        boolean result = false;
        long currTime = System.currentTimeMillis();
        if (food.checkDate(currTime) >= 25 && food.checkDate(currTime) < 75) {
            result = true;
        } else if (food.checkDate(currTime) >= 75 && food.checkDate(currTime) < 100) {
            food.setDiscount(food.getPossibleDiscount());
            result = true;
        }
        return result;
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
