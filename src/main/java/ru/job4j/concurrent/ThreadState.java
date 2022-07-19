package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println("Имя первого потока " + Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println("Имя второго потока " + Thread.currentThread().getName())
        );
        System.out.println("Первый поток создан " + first.getName() + " STATE is " + first.getState());
        System.out.println("Первый поток создан " + second.getName() + " STATE is " + second.getState());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED
                || second.getState() != Thread.State.TERMINATED) {
            System.out.println("Состояние первого потока " + first.getState()
                    + "; \nСостояние второго потока " + second.getState());
        }
        System.out.println(first.getName() + " STATE is " + first.getState());
        System.out.println(second.getName() + " STATE is " + second.getState());
    }
}
