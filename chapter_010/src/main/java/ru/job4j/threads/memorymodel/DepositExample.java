package ru.job4j.threads.memorymodel;

public class DepositExample implements Runnable {
    private static boolean flag = false;
    public void changeFlag() {
        flag = true;
        System.out.println("Flag was changed");
    }

    @Override
    public void run() {
        while (!flag) {
            try {
                Thread.sleep(1000);
                System.out.println("Before change");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("After change.");
    }

    public static void main(String[] args) throws InterruptedException {
        DepositExample de = new DepositExample();
        Thread t = new Thread(de);
        t.start();
        Thread.sleep(5000);
        de.changeFlag();
    }
}
