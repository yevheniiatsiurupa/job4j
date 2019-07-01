package ru.job4j.lsp;

public class StorageBlock {
    private Warehouse warehouse = new Warehouse();
    private Shop shop = new Shop();
    private Trash trash = new Trash();

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Shop getShop() {
        return shop;
    }

    public Trash getTrash() {
        return trash;
    }
}
