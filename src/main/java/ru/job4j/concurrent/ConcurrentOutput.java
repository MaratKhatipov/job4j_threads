package ru.job4j.concurrent;


public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> {
                    Thread.currentThread().setName("Поток \"another\" - ");
                    System.out.println(Thread.currentThread().getName()
                            + " приоритет: " + Thread.currentThread().getPriority());
                }
        );
        Thread second = new Thread(
                () -> {
                    Thread.currentThread().setName("Поток \"second\" - ");
                    System.out.println(Thread.currentThread().getName()
                            + " приоритет: " + Thread.currentThread().getPriority());
                }
        );
        another.start();
        second.start();
        System.out.println(Thread.currentThread().getName()
                + " приоритет: " + Thread.currentThread().getPriority());
    }
}
