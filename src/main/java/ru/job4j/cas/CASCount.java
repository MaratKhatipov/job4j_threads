package ru.job4j.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void incrementValue() {
        int tmp;
        int ref;
        do {
            ref = count.get();
            tmp = ref + 1;
        } while (!count.compareAndSet(ref, tmp));
    }

    public int getValue() {
        return count.get();
    }
}
