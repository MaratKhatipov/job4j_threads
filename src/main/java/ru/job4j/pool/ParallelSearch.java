package ru.job4j.pool;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import static java.lang.Math.max;

public class ParallelSearch<T> extends RecursiveTask<Integer> {
    private final static int LIMIT = 10;
    private final T[] array;
    private final int from;
    private final int to;

    private final T element;

    public ParallelSearch(T[] array, int from, int to, T element) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.element = element;
    }

    /**
     * The main computation performed by this task.
     * @return the result of the computation
     */
    @Override
    protected Integer compute() {
        int len = to - from;
        if (len < LIMIT) {
            return linearSearchIndex(element, from, to);
        }
        int mid = len / 2;
        ParallelSearch<T> leftSearcher = new ParallelSearch<>(array, from, mid, element);
        ParallelSearch<T> rightSearcher = new ParallelSearch<>(array, mid + 1, to, element);
        leftSearcher.fork();
        rightSearcher.fork();
        int left = leftSearcher.join();
        int right = rightSearcher.join();
        return max(left, right);
    }

    private int linearSearchIndex(T element, int from, int to) {
        for (int i = from; i <= to; i++) {
            if (element.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    public static <T> int indexOf(T[] arr, T elem) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelSearch<>(arr, 0, arr.length - 1, elem));
    }
}
