package ru.job4j.lsp;

public class Food {
    private String name;
    private long expireDate;
    private long createDate;
    private int price;
    private int discount;

    public Food(String name, long expireDate, long createDate, int price) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
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

    public void setDiscount(int discount) {
        this.discount = discount;
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
