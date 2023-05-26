package leetcode.q226_翻转二叉树.q0_模版;

import structure.TreeNode;


import java.util.List;

public class Test {
    public static void main(String[] args) {

    }
}

/**
 * 题解目录
 * {@link Solution1}
 */
class Solution1 implements Question {

    @Override
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            invertTree(root.left);
        }
        if (root.right != null) {
            invertTree(root.right);
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }
}