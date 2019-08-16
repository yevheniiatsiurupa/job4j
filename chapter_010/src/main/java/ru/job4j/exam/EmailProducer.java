package ru.job4j.exam;

import ru.job4j.threads.pool.email.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailProducer {
    private ExecutorService executor;
    private List<User> users;
    private long randInterval;
    private int countEmailsSent = 0;

    public EmailProducer(List<User> users, long randInterval) {
        this.executor = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
        this.users = users;
        this.randInterval = randInterval;
    }

    public long getRandInterval() {
        return randInterval;
    }

    public int getCountEmailsSent() {
        return countEmailsSent;
    }

    public void sendEmails(String message) {
        for (User tmp : users) {
            Runnable task = new EmailTask(tmp, message);
            this.executor.submit(task);
            this.countEmailsSent++;
        }
    }

    public void shutProducer() {
        this.executor.shutdown();
    }
}
