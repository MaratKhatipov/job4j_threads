package ru.job4j;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SimpleBlockingQueueTest {

    @Test
    void whenProducerAndConsumerInteract() throws InterruptedException {
        SimpleBlockingQueue<Integer> test = new SimpleBlockingQueue<>(5);
        int[] expect = {1, 2, 3, 4, 5};
        int[] actual = new int[5];
        Thread producer = new Thread(() -> {
            for (int i : expect) {
                try {
                    test.offer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < actual.length; i++) {
                try {
                    actual[i] = test.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        System.out.println(Arrays.toString(actual));
        assertArrayEquals(expect, actual);
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        System.out.println(buffer);
        assertThat(buffer).isEqualTo((Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }
}