package ru.job4j.threads.pool.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version 1.0.
 * @since 03/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class EmailNotification {
    private ExecutorService pool;

    public EmailNotification() {
        this.pool = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
    }

    /**
     * Метод добавляет задачу отправления письма в пул.
     * Здесь определяется тема, содержание письма.
     * @param user пользователь, которому отправляется письмо.
     */
    public void emailTo(User user) {
        String subject = String.format("Notification %s to email %s", user.getUsername(), user.getEmail());
        String body = String.format("Add a new event to %s", user.getUsername());
        pool.submit(new Runnable() {
            @Override
            public void run() {
                this.send(subject, body, user.getEmail());
            }

            /**
             * Метод для отправления письма пользователю.
             * (пока не реализован)
             * @param subject тема письма.
             * @param body текст письма.
             * @param email адрес электронной почты.
             */
            public void send(String subject, String body, String email) {

            }
        });
    }

    /**
     * Метод закрывает пул потоков pool и ждет окончания выполнения задач.
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
