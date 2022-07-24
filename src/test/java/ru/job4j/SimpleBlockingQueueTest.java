package ru.job4j;

import org.junit.jupiter.api.Test;

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
                test.offer(i);
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
        assertArrayEquals(expect, actual);
    }
}