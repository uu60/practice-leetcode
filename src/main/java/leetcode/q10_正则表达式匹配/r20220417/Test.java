package leetcode.q10_正则表达式匹配.r20220417;



public class Test {
    public static void main(String[] args) {

    }
}

/**
 * 题解目录
 * {@link Solution1} 复习动态规划
 * {@link Solution2} 复习递归
 */
class Solution2 implements Question {

    @Override
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        if (sLen > 0 && pLen == 0) {
            return false;
        }
        if (s.length() == 0) {
            if (p.length() % 2 != 0) {
                return false;
            }
            for (int i = 1; i < p.length(); i+=2) {
                if (p.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }

        if (p.charAt(pLen - 1) == '*') {
            if (p.charAt(pLen - 2) == '.' || s.charAt(sLen - 1) == p.charAt(pLen - 2)) {
                return isMatch(s, p.substring(0, pLen - 2)) // 不匹配这个*
                        || isMatch(s.substring(0, sLen - 1), p);
            }
            return isMatch(s, p.substring(0, pLen - 2));
        } else if (p.charAt(pLen - 1) == '.' || p.charAt(pLen - 1) == s.charAt(sLen - 1)) {
            return isMatch(s.substring(0, sLen - 1), p.substring(0, pLen - 1));
        }
        return false;
    }
}

class Solution1 implements Question {

    /**
     * @param s 待验证字符串
     * @param p 正则表达式
     * @return
     */
    @Override
    public boolean isMatch(String s, String p) {
        // dp[i][j]表示s前i个字符和p前j个字符的匹配情况
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;

        for (int i = 0; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                char c = p.charAt(j - 1);
                if (c == '*') { // 此时j一定大于1
                    // c1是*前的字符
                    char c1 = p.charAt(j - 2);
                    dp[i][j] = dp[i][j - 2]
                            || (i > 0 && (c1 == '.' || c1 == s.charAt(i - 1)) && dp[i - 1][j]);
                } else if (i > 0 && (c == '.' || c == s.charAt(i - 1))) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
