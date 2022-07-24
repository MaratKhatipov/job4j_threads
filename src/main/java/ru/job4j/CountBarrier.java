package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class CountBarrier {
    @GuardedBy("monitor")
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
            System.out.println("count in method - " + count);
            monitor.notifyAll();
        }
    }

    public void await() {
        synchronized (monitor) {
            while (count < total) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountBarrier test = new CountBarrier(5);
        Thread first = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName());
                    for (int i = 0; i < 6; i++) {
                        test.count();
                    }

                }, "count method"
        );

        Thread second = new Thread(
                () -> {
                    System.out.println("Waiting...");
                    test.await();
                    System.out.println("Done!");
                }
        );
        first.start();
        second.start();
    }
}
