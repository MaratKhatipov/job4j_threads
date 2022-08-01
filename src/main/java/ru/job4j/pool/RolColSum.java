package ru.job4j.pool;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums() {
        }

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }

        @Override
        public String toString() {
            return "Sums{"
                    + "сумма строк= " + rowSum
                    + ", сумма столбцов= " + colSum
                    + '}';
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            getSum(matrix, sums, i);
        }
        System.out.println(Arrays.toString(sums));
        return sums;
    }

    private static Sums getSum(int[][] matrix, Sums[] sums, int i) {
        sums[i] = new Sums();
        for (int j = 0; j < matrix.length; j++) {
            sums[i].rowSum += matrix[i][j];
            sums[i].colSum += matrix[j][i];
        }
        return new Sums(sums[i].rowSum, sums[i].colSum);
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] result = new Sums[matrix.length];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int i = 0; i < result.length; i++) {
            futures.put(i, getTask(matrix, result, i));
        }
        for (Integer key : futures.keySet()) {
            result[key] = futures.get(key).get();
        }
        System.out.println(Arrays.toString(result));
        return result;
    }

    public static CompletableFuture<Sums> getTask(int[][] matrix, Sums[] sums, int i) {
        return CompletableFuture.supplyAsync(() -> getSum(matrix, sums, i));

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        long start1 = System.nanoTime();
        sum(matrix);
        long end1 = System.nanoTime() - start1;
        System.out.println("Обычный = " + end1);

        long start2 = System.nanoTime();
        asyncSum(matrix);
        long end2 = System.nanoTime() - start2;
        System.out.println("асинхронный = " + end2);
        System.out.println("разница = " + (end1 - end2));
    }
}
