package ru.job4j.pool;

import ru.job4j.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    int size = Runtime.getRuntime().availableProcessors();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public ThreadPool() {
        for (int i = 0; i <= size; i++) {
            Thread threadInit = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
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

    public synchronized void work(Runnable job) throws InterruptedException {
            tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);

    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool test = new ThreadPool();
        for (int i = 0; i < 15; i++) {
            test.work(() -> System.out.println("Execute " + Thread.currentThread().getName()));
        }
        test.shutdown();

        int[] index = {0};
        ThreadPool test2 = new ThreadPool();
        for (int i = 0; i < 10; i++) {
            test2.work(() -> index[0]++);
            Thread.sleep(10);
            System.out.println(index[0]);
        }
        test2.shutdown();
    }
}

