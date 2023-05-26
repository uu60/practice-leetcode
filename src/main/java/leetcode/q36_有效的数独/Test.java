package leetcode.q36_有效的数独;



import java.util.HashSet;

public class Test {
    public static void main(String[] args) {

    }
}


/**
 * 题解目录
 * {@link Solution1} 暴力
 * {@link Solution2} 一次遍历，记录数字出现次数
 */
class Solution2 implements Question {

    @Override
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] lines = new int[9][9];
        int[][] boxes = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int ci = c - '0' - 1;
                    int boxi = i / 3 * 3 + j / 3;
                    if (rows[i][ci] == 1
                            || lines[j][ci] == 1
                            || boxes[boxi][ci] == 1
                    ) {
                        return false;
                    }
                    rows[i][ci] = 1;
                    lines[j][ci] = 1;
                    boxes[boxi][ci] = 1;
                }
            }
        }
        return true;
    }
}

class Solution1 implements Question {

    /**
     * 暴力破解
     *
     * @param board
     * @return
     */
    @Override
    public boolean isValidSudoku(char[][] board) {
        // 用于记录是否重复
        HashSet<Character> set = new HashSet<>();
        HashSet<Character> set1 = new HashSet<>();
        // 先查看列和行是否有问题
        for (int i = 0; i < 9; i++) {
            set.clear();
            set1.clear();
            // 检查列
            for (int j = 0; j < 9; j++) {
                if (set.contains(board[i][j])) {
                    return false;
                }
                if ('.' != board[i][j]) {
                    set.add(board[i][j]);
                }
            }
            // 检查行
            for (char[] chars : board) {
                if (set1.contains(chars[i])) {
                    return false;
                }
                if ('.' != chars[i]) {
                    set1.add(chars[i]);
                }
            }
        }
        // 检查3*3
        set.clear();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                set.clear();
                for (int k = i * 3; k < i * 3 + 3; k++) {
                    for (int l = j * 3; l < j * 3 + 3; l++) {
                        if (set.contains(board[k][l])) {
                            return false;
                        }
                        if ('.' != board[k][l]) {
                            set.add(board[k][l]);
                        }
                    }
                }
            }
        }
        return true;
    }
}