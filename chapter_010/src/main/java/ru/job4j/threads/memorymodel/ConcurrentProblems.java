package ru.job4j.threads.memorymodel;

public class ConcurrentProblems {
    private static int shared = 0;

    public static void main(String[] args) {
        new Thread(new ChangeOne()).start();
        new Thread(new ChangeTwo()).start();
    }

    static class ChangeOne implements Runnable {

        @Override
        public void run() {
            int localValue = shared;
            while (localValue < 5) {
                if (localValue != shared) {
                    System.out.println("Value was changed:" + shared);
                    localValue = shared;
                }
            }
        }
    }

    static class ChangeTwo implements Runnable {

        @Override
        public void run() {
            int localValue = shared;
            while (shared < 5) {
                shared = ++localValue;
                System.out.println("Incrementing shared number: " + shared);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
