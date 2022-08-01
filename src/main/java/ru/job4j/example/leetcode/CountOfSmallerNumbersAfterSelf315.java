package ru.job4j.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CountOfSmallerNumbersAfterSelf315 {
    public List<Integer> countSmaller(int[] nums) {
        int length = nums.length;
        List<Integer> result = new ArrayList<>();
        ArrayList<Integer> indexPos = new ArrayList<>();
        for (Integer num : nums) {
            indexPos.add(num);
        }
        Collections.sort(indexPos);
        Arrays.stream(nums).map(num -> binarySearch(indexPos, num)).forEachOrdered(index -> {
            result.add(index);
            indexPos.remove(index);
        });
        return result;
    }

    public int binarySearch(ArrayList<Integer> arr, int target) {
        int start = 0;
        int end = arr.size() - 1;
        int mid = 0;
        while (start <= end) {
            mid = start + (end - start) / 2;
            int value = arr.get(mid);
            if (value < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (arr.get(start) == target) {
            return start;
        }
        return mid;
    }

}
