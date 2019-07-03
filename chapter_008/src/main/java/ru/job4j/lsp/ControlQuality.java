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
        long date3 = new GregorianCalendar(2019, Calendar.JULY, 4).getTimeInMillis();
        long date4 = new GregorianCalendar(2019, Calendar.JULY, 30).getTimeInMillis();

        Food item1 = new Vegetables("carrot", date2, date1, 100, 20, true);
        Food item2 = new Fruit("apple", date3, date1, 80, 30, false);
        Food item3 = new Dairy("milk", date2, date1, 60, 40, false);
        Food item4 = new Dairy("cheese", date4, date1, 60, 40, false);
        Food item5 = new Fruit("banana", date2, date1, 100, 20, true);

        StorageBlock sb = new StorageBlock();
        Warehouse one = new Warehouse();           // старый склад, сюда не попадают продукты
        Storage two = new WarehouseExtra(one);     // склад, куда попадают продукты вместо one.
        Storage three = new Shop();                // магазин
        Trash four = new Trash();                  // свалка (не для переработки)
        Storage five = new TrashRecycle(four);     // свалка (для переработки, обычная температура)
        Storage six = new StorageColdTemp(five);   // свалка (для переработки, низкая температура)

        sb.addStorage(two);
        sb.addStorage(three);
        sb.addStorage(six);
        ControlQuality controlQuality = new ControlQuality(sb);
        controlQuality.addProduct(item1);
        controlQuality.addProduct(item2);
        controlQuality.addProduct(item3);
        controlQuality.addProduct(item4);
        controlQuality.addProduct(item5);

        System.out.println(one.getList());
        System.out.println(two.getList());
        System.out.println(three.getList());
        System.out.println(four.getList());
        System.out.println(five.getList());
        System.out.println(six.getList());
    }
}
