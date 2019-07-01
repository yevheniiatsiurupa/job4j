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
        long date3 = new GregorianCalendar(2019, Calendar.JULY, 2).getTimeInMillis();
        long date4 = new GregorianCalendar(2019, Calendar.JULY, 30).getTimeInMillis();

        Food item1 = new Vegetables("carrot", date4, date1, 100, 20);
        Food item2 = new Fruit("apple", date3, date1, 80, 30);
        Food item3 = new Dairy("milk", date2, date1, 60, 40);

        StorageBlock sb = new StorageBlock();
        ControlQuality controlQuality = new ControlQuality(sb);
        controlQuality.addProduct(item1);
        controlQuality.addProduct(item2);
        controlQuality.addProduct(item3);

        for (Storage tmp : sb.getStorage()) {
            System.out.println(tmp.getList());
        }
    }
}
