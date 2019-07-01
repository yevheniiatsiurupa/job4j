package ru.job4j.isp;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Menu {
    private Set<MenuElement> menu = new TreeSet<>(new Comparator<MenuElement>() {
        @Override
        public int compare(MenuElement o1, MenuElement o2) {
            return o1.getKey().compareTo(o2.getKey());
        }
    });

    public void addMenu(MenuElement elem) {
        menu.add(elem);
    }

    public String showFullMenu() {
        StringBuilder sb = new StringBuilder();
        String ln = System.lineSeparator();
        for (MenuElement tmp : menu) {
            sb.append(tmp.showMenu());
            sb.append(ln);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Menu testMenu = new Menu();
        MenuElement m1 = new MenuOption("Option1", "1.");
        MenuElement m2 = new MenuOption("Option1", "2.");
        MenuElement mm11 = new SubMenuOption("Option1-1", "1.", m1);
        MenuElement mm12 = new SubMenuOption("Option1-2", "2.", m1);
        MenuElement mm111 = new SubMenuOption("Option1-1-1", "1.", mm11);
        MenuElement mm21 = new SubMenuOption("Option2-1", "1.", m2);
        MenuElement mm22 = new SubMenuOption("Option2-2", "2.", m2);

        testMenu.addMenu(m1);
        testMenu.addMenu(m2);
        testMenu.addMenu(mm11);
        testMenu.addMenu(mm12);
        testMenu.addMenu(mm22);
        testMenu.addMenu(mm21);
        testMenu.addMenu(mm111);

        System.out.println(testMenu.showFullMenu());
    }
}
