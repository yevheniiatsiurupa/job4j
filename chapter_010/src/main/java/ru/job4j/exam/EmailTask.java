package ru.job4j.exam;

import ru.job4j.threads.pool.email.User;

public class EmailTask implements Runnable {
    private User user;
    private String message;

    public EmailTask(User user, String message) {
        this.user = user;
        this.message = message;
    }

    @Override
    public void run() {
        this.send();
    }

    public void send() {
        System.out.println(String.format("User: %s, email: %s.%sMessage: %s",
                this.user.getUsername(), this.user.getEmail(), System.lineSeparator(), this.message));
    }
}
