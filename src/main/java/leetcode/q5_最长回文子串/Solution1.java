package leetcode.q5_最长回文子串;

/**
 * @author octopus
 * @since 2023/4/28 16:55
 */
public class Solution1 implements Question {

    public static void main(String[] args) {
        System.out.println(new Solution1().longestPalindrome("abcbasdsd"));
    }

    @Override
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        // dp[i][j]表示s从[i, j]子串是回文
        boolean[][] dp = new boolean[s.length()][s.length()];
        // 一个字符是回文
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = true;
        }
        // 记录结果
        String ans = String.valueOf(s.charAt(0));
        // 按长度开始遍历
        for (int len = 2; len <= s.length(); len++) {
            // i是起始位
            for (int i = 0; i < s.length() - len + 1; i++) {
                // 子串首尾相同并且中间是回文
                if (s.charAt(i) == s.charAt(i + len - 1) && (len <= 2 || dp[i + 1][i + len - 2])) {
                    dp[i][i + len - 1] = true;
                    ans = ans.length() < len ? s.substring(i, i + len) : ans;
                }
            }
        }

        return ans;
    }
}
