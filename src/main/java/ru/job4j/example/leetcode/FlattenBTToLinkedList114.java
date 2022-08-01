package ru.job4j.example.leetcode;

public class FlattenBTToLinkedList114 {

    public void flatten2(TreeNode root) {
        while (root != null) {
            TreeNode left = root.left;
            TreeNode right = root.right;
            root.left = null;

            if (left == null) {
                root = right;
                continue;
            }

            TreeNode leftRight = left;
            while (leftRight.right != null) {
                leftRight = leftRight.right;
            }

            leftRight.right = right;
            root.right = left;
            root = root.right;
        }
    }

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right = left;

        while (root.right != null) {
            root = root.right;
        }
        root.right = right;
    }

    public static void main(String[] args) {
        TreeNode expected = new TreeNode(
                1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(5, null, new TreeNode(6))
        );
        new FlattenBTToLinkedList114().flatten(expected);
        System.out.println(expected);
    }
}
