package leetcode.q7_整数反转.r20220420;



public interface Question {
    int reverse(int x);
}

class Test {
    public static void main(String[] args) {

    }
}

/**
 * 题解目录
 * {@link Solution1}
 */
class Solution1 implements Question {

    @Override
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            if (res > Integer.MAX_VALUE / 10 || res < Integer.MIN_VALUE / 10) {
                return 0;
            }
            res = res * 10 + (x % 10);
            x /= 10;
        }
        return res;
    }
}
