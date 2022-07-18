package ru.job4j.concurrent;

/*
Давайте теперь поговорим о конструкторе класса java.lang.Thread.

Конструктор этого класса принимает функциональный интерфейс java.lang.Runnable.
Это интерфейс имеет один метод public void run().
Методы определенные в этом методе будут выполняться в многозадачной среде.

Чтобы не создавать анонимный класс, в примере выше использовалось лямбда-выражение.
 */
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
