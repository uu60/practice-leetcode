package leetcode.q29_两数相除;



public interface Question {
    int divide(int dividend, int divisor);
}

class Test {
    public static void main(String[] args) {
    }
}
/**
 * 题解目录
 * {@link Solution1}超时
 */
class Solution1 implements Question {

    @Override
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && (divisor == 1 || divisor == -1)) {
            if (divisor == -1)
                return Integer.MAX_VALUE;
            return Integer.MIN_VALUE;
        }
        int res = 0;
        int temp = 0;
        boolean dif = false;
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            dif = true;
            if (dividend > 0) {
                dividend = -dividend;
            } else {
                divisor = -divisor;
            }
        }
        while (divisor > 0 ? dividend - temp >= divisor : dividend - temp <= divisor) {
            temp += divisor;
            res++;
        }
        return dif ? -res : res;
    }
}
