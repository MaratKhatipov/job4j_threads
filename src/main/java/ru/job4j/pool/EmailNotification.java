package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        String subject = "Notification " + user.getUsername() + " to email " + user.getEmail();
        String body = " Add a new event to " + user.getUsername();
        pool.submit(() -> {
            System.out.println("Execute " + Thread.currentThread().getName());
            send(subject, body, user.getEmail());
        });
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    void send(String subject, String body, String email) {
        System.out.println(subject + body);
    }

    public static void main(String[] args) {
        User user1 = new User();
        user1.setUsername("Fedor");
        user1.setEmail("test@eample.ru");
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.emailTo(user1);
        emailNotification.close();
    }
}
