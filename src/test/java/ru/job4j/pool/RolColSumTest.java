package ru.job4j.pool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

class RolColSumTest {

    @Test
    void asyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] serialVersion = RolColSum.sum(matrix);
        RolColSum.Sums[] asyncVersion = RolColSum.asyncSum(matrix);
        Assertions.assertArrayEquals(serialVersion, asyncVersion);
    }
}