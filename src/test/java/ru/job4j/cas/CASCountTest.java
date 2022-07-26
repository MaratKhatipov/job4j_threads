package ru.job4j.cas;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;


class CASCountTest {

    @Test
    void whenIncrementValueInThreeThreads() {
        CASCount count = new CASCount();
        Thread one = new Thread(
                () -> IntStream.range(0, 5).forEach(x -> count.incrementValue())
        );
        Thread two = new Thread(
                () -> IntStream.range(0, 5).forEach(x -> count.incrementValue())
        );
        Thread three = new Thread(
                () -> IntStream.range(0, 5).forEach(x -> count.incrementValue())
        );
        one.start();
        two.start();
        three.start();

        try {
            one.join();
            two.join();
            three.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(count.getValue());
        Assertions.assertThat(count.getValue()).isEqualTo(15);
    }

    @Test
    void whenIncrementValueInOneThread() throws InterruptedException {
        CASCount count = new CASCount();
        Thread one = new Thread(
                () -> IntStream.range(0, 5).forEach(x -> count.incrementValue())
        );
        one.start();
        one.join();
        Assertions.assertThat(count.getValue()).isEqualTo(5);
    }
}