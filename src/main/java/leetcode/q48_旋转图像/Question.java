package leetcode.q48_旋转图像;



import java.lang.reflect.Array;
import java.util.Arrays;

public interface Question {
    void rotate(int[][] matrix);
}

class Test {
    public static void main(String[] args) {

    }
}

/**
 *      题解目录      时间击败 空间击败 时间复杂度 空间复杂度 描述
 * {@link Solution1} 100    8       O(n)       O(n)    使用额外空间
 * {@link Solution2} 100    28       O(n)      O(1)    反转代替
 * {@link Solution3} 100    12       O(n)      O(1)    临时值旋转
 *
 */
class Solution3 implements Question {

    @Override
    public void rotate(int[][] matrix) {
        int len = matrix.length;

        for (int i = 0; i <= len / 2; i++) {
            for (int j = 0; j <= (len + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[len - j - 1][i];
                matrix[len - j - 1][i] = matrix[len - i - 1][len - j - 1];
                matrix[len - i - 1][len - j - 1] = matrix[j][len - i - 1];
                matrix[j][len - i - 1] = temp;
            }
        }
    }
}

class Solution2 implements Question {

    @Override
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        
        // 换行
        for (int i = 0; i < len / 2; i++) {
            for (int j = 0; j < len; j++) {
                swap(matrix, i, j, len - i - 1, j);
            }
        }

        // 换列
        for (int i = 0; i < len / 2; i++) {
            for (int j = 0; j < len; j++) {
                swap(matrix, i, j, i, len - j - 1);
            }
        }
        
        // 换主对角线
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }
    
    private void swap(int[][] matrix, int i, int j, int i1, int j1) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[i1][j1];
        matrix[i1][j1] = temp;
    }
}

class Solution1 implements Question {

    @Override
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        int[][] backup = new int[len][len];
        for (int i = 0; i < len; i++) {
            System.arraycopy(matrix[i], 0, backup[i], 0, len);
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                matrix[i][j] = backup[len - j - 1][i];
            }
        }
    }
}