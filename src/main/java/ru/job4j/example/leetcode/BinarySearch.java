package ru.job4j.example.leetcode;

import java.util.Arrays;

public class BinarySearch {
    public static int binarySearchLast(int[] nums, int target) {
        int start1 = 0;
        int end = nums.length - 1;
        int index1 = -1;
        while (start1 <= end) {
            int mid = start1 + (end - start1) / 2;
            if (nums[mid] <= target) {
                start1 = mid + 1;
            } else {
                end = mid - 1;
            }
            if (nums[mid] == target) {
                index1 = mid;
            }
        }
        return index1;
    }

    public static int binarySearchFirs(int[] nums, int target) {
        int start1 = 0;
        int end = nums.length - 1;
        int index1 = -1;
        while (start1 <= end) {
            int mid = start1 + (end - start1) / 2;
            if (nums[mid] < target) {
                start1 = mid + 1;
            } else {
                end = mid - 1;
            }
            if (nums[mid] == target) {
                index1 = mid;
            }
        }
        return index1;
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] res = {binarySearchFirs(nums, 8), binarySearchLast(nums, 8)};
        System.out.println(Arrays.toString(res));
    }
}
