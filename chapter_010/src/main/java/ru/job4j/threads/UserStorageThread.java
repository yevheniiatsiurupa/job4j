package ru.job4j.threads;

public class UserStorageThread  extends Thread {
    private UserStorage storage;
    private int fromId;
    private int toId;
    private int amount;

    public UserStorageThread(UserStorage storage, int fromId, int toId, int amount) {
        this.storage = storage;
        this.fromId = fromId;
        this.toId = toId;
        this.amount = amount;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean result = storage.transfer(fromId, toId, amount);
        if (result) {
            System.out.println("Successful operation.");
        }
    }
}
