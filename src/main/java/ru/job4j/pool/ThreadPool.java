package ru.job4j.pool;

import ru.job4j.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(8);


    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i <= size; i++) {
            Thread threadInit = new Thread(() -> {
                while (!tasks.isEmpty() || !Thread.currentThread().isInterrupted()) {
                    try {
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            threads.add(threadInit);
            threadInit.start();
        }
    }

    public synchronized void work(Runnable job) {
        try {
            tasks.offer(job);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);

    }

    public static void main(String[] args) {
        int[] index = {0};
        ThreadPool test = new ThreadPool();
        for (int i = 0; i < 10; i++) {
            test.work(() -> {
                index[0]++;
            });
        }
        System.out.print(index[0]);
        test.shutdown();
    }
}
