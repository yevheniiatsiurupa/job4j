package ru.job4j.lsp;

public class Food {
    private String name;
    private long expireDate;
    private long createDate;
    private int price;
    private int discount;
    private int possibleDiscount;
    private boolean recycle;
    private boolean coldTemp;

    public Food(String name, long expireDate, long createDate, int price, int possibleDiscount, boolean recycle, boolean coldTemp) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.possibleDiscount = possibleDiscount;
        this.recycle = recycle;
        this.coldTemp = coldTemp;
    }

    public String getName() {
        return name;
    }

    public long getExpireDate() {
        return expireDate;
    }

    public long getCreateDate() {
        return createDate;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public int getPossibleDiscount() {
        return possibleDiscount;
    }

    public boolean isRecycle() {
        return recycle;
    }

    public boolean isColdTemp() {
        return coldTemp;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    /**
     * Метод для проверки срока годности продукта.
     * @return процент истечения срока годности относительно текущего времени.
     */
    public double checkDate(long timeNow) {
        long expireDate = this.getExpireDate();
        long createDate = this.getCreateDate();
        long totalTime = expireDate - createDate;
        long currTime = timeNow - createDate;
        return (double) currTime / totalTime * 100L;
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}
