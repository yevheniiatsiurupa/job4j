package ru.job4j.isp;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class MenuNew {
    private Set<MenuElem> fullMenu = new TreeSet<>(new Comparator<MenuElem>() {
        @Override
        public int compare(MenuElem o1, MenuElem o2) {
            return o1.getKey().compareTo(o2.getKey());
        }
    });

    public void addElem(MenuElem elem) {
        fullMenu.add(elem);
    }

    public String showFullMenu() {
        StringBuilder sb = new StringBuilder();
        String ln = System.lineSeparator();
        for (MenuElem tmp : fullMenu) {
            sb.append(tmp.showElem("---"));
            sb.append(ln);

        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MenuElem m1 = new MenuElem("Option 1", "1.");
        MenuElem m2 = new MenuElem("Option 2", "2.");
        MenuElem m11 = new MenuElem("Option 1-1", "1.");
        MenuElem m12 = new MenuElem("Option 1-2", "2.");
        MenuElem m111 = new MenuElem("Option 1-1-1", "1.");
        MenuElem m21 = new MenuElem("Option 2-1", "1.");
        MenuElem m22 = new MenuElem("Option 2-2", "2.");
        m1.addSub(m11);
        m1.addSub(m12);
        m11.addSub(m111);
        m2.addSub(m21);
        m2.addSub(m22);

        MenuNew mn = new MenuNew();
        mn.addElem(m1);
        mn.addElem(m2);
        System.out.println(mn.showFullMenu());
    }
}
