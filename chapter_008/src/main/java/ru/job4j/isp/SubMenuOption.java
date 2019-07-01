package ru.job4j.isp;


public class SubMenuOption extends MenuElement implements SimpleSubMenu {
    private MenuElement parent;

    public SubMenuOption(String name, String key, MenuElement menuElement) {
        super(name, key);
        this.parent = menuElement;
        this.setKey(parent.getKey() + key);
    }


    public String showMenu(String starting) {
        return String.format("%s%s %s", starting, this.getKey(), this.getName());
    }
}
