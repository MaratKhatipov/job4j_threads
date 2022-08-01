package ru.job4j.example.leetcode;

import java.util.LinkedList;
import java.util.Queue;


public class PartitionList86 {
    public ListNode partition(ListNode head, int x) {
        Queue<Integer> firstStack = new LinkedList<>();
        Queue<Integer> secondStack = new LinkedList<>();

        ListNode current = head;
        while (current != null) {
            if (current.val < x) {
                firstStack.offer(current.val);
            } else {
                secondStack.offer(current.val);
            }
            current = current.next;
        }
        current = head;

        while (!firstStack.isEmpty()) {
            current.val = firstStack.remove();
            current = current.next;
        }
        while (!secondStack.isEmpty()) {
            current.val = secondStack.remove();
            current = current.next;
        }
        return head;
    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2))))));
        PartitionList86 test = new PartitionList86();
        System.out.println(test.partition(l1, 3));
    }
}
