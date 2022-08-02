package ru.job4j.example.leetcode;

import java.util.Arrays;

public class KthSmallest378 {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int[] res = new int[n * n];
        int index = 0;
        for (int[] ints : matrix) {
            for (int j = 0; j < n; j++) {
                res[index++] = ints[j];
            }
        }
        Arrays.sort(res);
        return res[k - 1];
    }
}
