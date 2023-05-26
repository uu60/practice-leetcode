package leetcode.q9_回文数;

/**
 * @author Du
 * @description
 * @time 2022/1/6 12:58
 */
public class Solution1 implements Question {

    // My Answer
    @Override
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (!(s.charAt(i) == s.charAt(j))) {
                return false;
            }
        }
        return true;
    }
}
