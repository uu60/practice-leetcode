package leetcode.q51_N皇后;



import java.util.ArrayList;
import java.util.List;

public interface Question {
    List<List<String>> solveNQueens(int n);
}

class Test {
    public static void main(String[] args) {

    }
}

/**
 * 题解目录      时间击败 空间击败 时间复杂度 空间复杂度 描述
 * {@link Solution1}   100    83     O(n!)       O(n)     回溯法
 */
class Solution1 implements Question {

    @Override
    public List<List<String>> solveNQueens(int n) {
        int[] board = new int[n];
        boolean[] lineUsed = new boolean[n];
        boolean[] slashUsed = new boolean[(n * 2 - 1) * 2];
        List<List<String>> ans = new ArrayList<>();
        backtrack(board, lineUsed, slashUsed, 0, ans);
        return ans;
    }

    private void backtrack(int[] board, boolean[] lineUsed, boolean[] slashUsed, int row, List<List<String>> ans) {
        // 满足需求
        if (row == board.length) {
            List<String> temp = new ArrayList<>();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < board.length; i++) {
                builder.append(".");
            }
            for (int r : board) {
                builder.replace(r, r + 1, "Q");
                temp.add(builder.toString());
                builder.replace(r, r + 1, ".");
            }
            ans.add(temp);
            return;
        }
        for (int line = 0; line < board.length; line++) {
            if (lineUsed[line] || slashUsed[row + line] || slashUsed[line - row + (2 * board.length - 1) + (board.length - 1)]) {
                continue;
            }
            board[row] = line;
            lineUsed[line] = true;
            slashUsed[row + line] = true;
            slashUsed[line - row + (2 * board.length - 1) + (board.length - 1)] = true;
            backtrack(board, lineUsed, slashUsed, row + 1, ans);
            board[row] = line;
            lineUsed[line] = false;
            slashUsed[row + line] = false;
            slashUsed[line - row + (2 * board.length - 1) + (board.length - 1)] = false;
        }
    }
}