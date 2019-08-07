package ru.job4j.exam;

import ru.job4j.threads.pool.email.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailProducer  extends Thread {
    private ExecutorService executor;
    private List<User> users;
    private long randInterval;

    public EmailProducer(List<User> users, long randInterval) {
        this.executor = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
        this.users = users;
        this.randInterval = randInterval;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            for (User tmp : users) {
                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        this.send();
                    }

                    public void send() {
                        System.out.println(String.format("Sent message: user - %s, email - %s.",
                                tmp.getUsername(), tmp.getEmail()));
                    }
                };
                this.executor.submit(task);
            }
            try {
                Thread.sleep(randInterval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        executor.shutdown();
    }
}
