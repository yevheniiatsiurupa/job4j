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
     * @param storageBlock
     */
    public ControlQuality(StorageBlock storageBlock) {
        this.storageBlock = storageBlock;
    }

    /**
     * Метод устанавливает хранилище, куда следует добавлять продукты.
     * @param storage ссылка на нужное хранилище.
     */
    private void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * Метод для проверки срока годности продукта.
     * @param item ссылка на продукт.
     * @return процент истечения срока годности относительно текущего времени.
     */
    private double checkDate(Food item) {
        long currentTime = System.currentTimeMillis();
        long expireDate = item.getExpireDate();
        long createDate = item.getCreateDate();
        long totalTime = expireDate - createDate;
        long currTime = currentTime - createDate;
        return (double) currTime / totalTime * 100L;
    }

    /**
     * Метод добавляет продукты в разные хранилища в зависимости от срока годности.
     * @param item продукт для добавления.
     * @param possibleDiscount возможная скидка на продукт.
     */
    public void addProduct(Food item, int possibleDiscount) {
        double expPercentage = this.checkDate(item);
        if (expPercentage < 25) {
            this.setStorage(this.storageBlock.getWarehouse());
            this.storage.add(item);
        } else if (expPercentage >= 25 && expPercentage < 75) {
            this.setStorage(this.storageBlock.getShop());
            this.storage.add(item);
        } else if (expPercentage >= 75 && expPercentage < 100) {
            item.setDiscount(possibleDiscount);
            this.setStorage(this.storageBlock.getShop());
            this.storage.add(item);
        } else {
            this.setStorage(this.storageBlock.getTrash());
            this.storage.add(item);
        }
    }

    public static void main(String[] args) {
        long date1 = new GregorianCalendar(2019, Calendar.JUNE, 25).getTimeInMillis();
        long date2 = new GregorianCalendar(2019, Calendar.JUNE, 30).getTimeInMillis();
        long date3 = new GregorianCalendar(2019, Calendar.JULY, 2).getTimeInMillis();
        long date4 = new GregorianCalendar(2019, Calendar.JULY, 30).getTimeInMillis();

        Food item1 = new Vegetables("carrot", date4, date1, 100);
        Food item2 = new Fruit("apple", date3, date1, 80);
        Food item3 = new Dairy("milk", date2, date1, 60);

        StorageBlock sb = new StorageBlock();
        ControlQuality controlQuality = new ControlQuality(sb);
        controlQuality.addProduct(item1, 20);
        controlQuality.addProduct(item2, 30);
        controlQuality.addProduct(item3, 40);

        System.out.println(sb.getShop().getList());
        System.out.println(sb.getWarehouse().getList());
        System.out.println(sb.getTrash().getList());
    }
}
