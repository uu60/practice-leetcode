package leetcode.q9_回文数;

/**
 * @author Du
 * @description
 * @time 2022/1/6 12:59
 */
public interface Question {
    boolean isPalindrome(int x);

    public static void main(String[] args) {
        // Validator.exec(Question.class, 2, 121);
    }
}

class Solution2 implements Question {

    @Override
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int len = String.valueOf(x).length();
        int mid = len / 2;
        int half = 0;
        for (int i = 0; i < mid; i++) {
            half *= 10;
            half += x % 10;
            x /= 10;
        }
        return half == x / ((len % 2 == 0) ? 1 : 10);
    }
}
