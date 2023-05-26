package leetcode.q427_建立四叉树;

import structure.quadtree.Node;


public interface Question {
    Node construct(int[][] grid);
}

class Test {
    public static void main(String[] args) {

    }
}

/**
 * 题解目录
 * {@link Solution1} 递归，击败100-71
 */
class Solution1 implements Question {

    @Override
    public Node construct(int[][] grid) {
        return recurse(grid, 0, 0, grid.length);
    }

    private Node recurse(int[][] grid, int x, int y, int len) {
        // 如果所有值都相同，那么没有子结点，值为相同值
        if (isSame(grid, x, y, len)) {
            return new Node(grid[x][y] == 1, true);
        }
        Node head = new Node(true, false);
        int newLen = len / 2;
        head.topLeft = recurse(grid, x, y, newLen);
        head.topRight = recurse(grid, x, y + newLen, newLen);
        head.bottomLeft = recurse(grid, x + newLen, y, newLen);
        head.bottomRight = recurse(grid, x + newLen, y + newLen, newLen);
        return head;
    }

    private boolean isSame(int[][] grid, int x, int y, int len) {
        if (len == 1) {
            return true;
        }
        int val = grid[x][y];
        for (int i = x; i < x + len; i++) {
            for (int j = y; j < y + len; j++) {
                if (grid[i][j] != val) {
                    return false;
                }
            }
        }
        return true;
    }
}