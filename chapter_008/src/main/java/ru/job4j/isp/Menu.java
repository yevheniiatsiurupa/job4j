package ru.job4j.isp;

import java.util.Arrays;
import java.util.List;

public class Menu {
   private List<MenuElem> menuElems;

    public Menu(List<MenuElem> menuElems) {
        this.menuElems = menuElems;
    }

    public void showFullMenu() {
        for (MenuElem tmp : menuElems) {
            System.out.println(tmp.showElem());
        }
    }

    public static void main(String[] args) {
        List<MenuElem> list = Arrays.asList(
                new MenuElem("Menu 1", "1."),
                new MenuElem("Menu 1-1", "1.1."),
                new MenuElem("Menu 2", "2."));
        Menu testMenu = new Menu(list);
        testMenu.showFullMenu();
    }
}
