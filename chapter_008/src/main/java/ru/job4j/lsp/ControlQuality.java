package ru.job4j.lsp;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ControlQuality {
    /**
     * Поле хранит ссылку на хранилище, куда следует добавлять продукт.
     */
    private Storage storage;

    /**
     * Ссылка на блок хранилищ Shop + Warehouse + Trash.
     */
    private StorageBlock storageBlock;

    /**
     * Конструктор.
     */
    public ControlQuality(StorageBlock storageBlock) {
        this.storageBlock = storageBlock;
    }



    /**
     * Метод добавляет продукты в разные хранилища в зависимости от срока годности.
     * @param item продукт для добавления.
     */
    public void addProduct(Food item) {
        this.storageBlock.add(item);
    }

    public static void main(String[] args) {
        long date1 = new GregorianCalendar(2019, Calendar.JUNE, 25).getTimeInMillis();
        long date2 = new GregorianCalendar(2019, Calendar.JUNE, 30).getTimeInMillis();
        long date3 = new GregorianCalendar(2019, Calendar.JULY, 3).getTimeInMillis();
        long date4 = new GregorianCalendar(2019, Calendar.JULY, 30).getTimeInMillis();

        Food item1 = new Vegetables("carrot", date2, date1, 100, 20, true);
        Food item2 = new Fruit("apple", date3, date1, 80, 30, false);
        Food item3 = new Dairy("milk", date2, date1, 60, 40, false);

        StorageBlock sb = new StorageBlock();
        sb.addStorage(new WarehouseExtra(new Warehouse()));
        sb.addStorage(new Shop());
        sb.addStorage(new StorageColdTemp(new TrashRecycle(new Trash())));
        sb.addStorage(new StorageNormalTemp(new TrashRecycle(new Trash())));
        sb.addStorage(new TrashNoRecycle(new Trash()));
        ControlQuality controlQuality = new ControlQuality(sb);
        controlQuality.addProduct(item1);
        controlQuality.addProduct(item2);
        controlQuality.addProduct(item3);

        for (Storage tmp : sb.getStorage()) {
            System.out.println(tmp.getList());
        }
    }
}
