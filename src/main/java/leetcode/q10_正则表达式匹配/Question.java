package leetcode.q10_正则表达式匹配;

public interface Question {
    boolean isMatch(String s, String p);

    static void main(String[] args) {
        System.out.println(new Solution7().isMatch("a", ".*..a*"));
    }
}

class Solution7 implements Question {

    @Override
    public boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        if (sLen == 0 && pLen == 0) {
            return true;
        }
        if (sLen == 0) {
            if (pLen % 2 != 0)
                return false;
            for (int i = 1; i < pLen; i += 2) {
                if (p.charAt(i) != '*') {
                    return false;
                }
            }
        }
        if ((sLen > 0 && pLen == 0)
                || (sLen > 0 && p.charAt(pLen - 1) != '*' && !match(s.charAt(sLen - 1), p.charAt(pLen - 1)))) {
            return false;
        }
        if (p.charAt(pLen - 1) == '*') {
            return isMatch(s, p.substring(0, pLen - 2)) || match(s.charAt(sLen - 1), p.charAt(pLen - 2)) && isMatch(s.substring(0, sLen - 1), p);
        }
        return isMatch(s.substring(0, sLen - 1), p.substring(0,
                pLen - 1));
    }

    private boolean match(char sc, char pc) {
        return pc == '.' || sc == pc;
    }
}

class Solution6 implements Question {

    @Override
    public boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;

        for (int i = 0; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2] || match(s, p, i, j - 1) && dp[i - 1][j];
                } else {
                    dp[i][j] = match(s, p, i, j) && dp[i - 1][j - 1];
                }
            }
        }
        return dp[sLen][pLen];
    }

    // 单个字符的匹配情况
    private boolean match(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}

class Solution5 implements Question {

    @Override
    public boolean isMatch(String s, String p) {
        // dp[i][j]表示s的前i个字符与正则表达式前j个字符的匹配情况
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;
        for (int i = 0; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2] || matches(s, p, i, j - 1) && dp[i - 1][j];
                } else {
                    dp[i][j] = matches(s, p, i, j) && dp[i - 1][j - 1];
                }
            }
        }
        return dp[sLen][pLen];
    }

    // 判断对应字符是否相等
    private boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}

/**
 * 复习动态规划
 */
class Solution4 implements Question {

    @Override
    public boolean isMatch(String s, String p) {
        // mem[i][j]表示s前i个和p前j个匹配情况
        boolean[][] mem = new boolean[s.length() + 1][p.length() + 1];
        // 空和空匹配
        mem[0][0] = true;

        for (int i = 0; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (i == 0) {
                    if (j % 2 == 0 && p.charAt(j - 1) == '*') {
                        mem[i][j] = mem[i][j - 2];
                    }
                    continue;
                }
                // 当前p最后不是*
                if (p.charAt(j - 1) != '*') {
                    // 最后一位匹配
                    if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
                        mem[i][j] = mem[i - 1][j - 1];
                    }
                } else { // p最后是*
                    // p倒数第二个匹配
                    if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
                        mem[i][j] = mem[i][j - 2] || mem[i - 1][j];
                    } else {
                        mem[i][j] = mem[i][j - 2];
                    }
                }
            }
        }
        return mem[s.length()][p.length()];
    }
}

/**
 * 复习递归
 */
class Solution3 implements Question {

    @Override
    public boolean isMatch(String s, String p) {
        int pLen = p.length();
        int sLen = s.length();
        // p为空
        if (pLen == 0) {
            // s不为空
            if (sLen > 0) {
                return false;
            }
            // s空
            return true;
        }
        // s空 p长度必须为偶数且偶数位为*
        if (sLen == 0) {
            if (pLen % 2 == 1) {
                return false;
            }
            // p为偶数长度
            for (int i = 1; i < pLen; i += 2) {
                if (p.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }

        char pLast = p.charAt(pLen - 1);
        // p最后不是*
        if (pLast != '*') {
            // s,p都不为空 但最后一位不匹配
            if (pLast != '.' && pLast != s.charAt(sLen - 1)) {
                return false;
            }
            // 最后一位匹配
            return isMatch(s.substring(0, sLen - 1), p.substring(0, pLen - 1));
        }
        // p最后是*
        // p*前不是. 并且不匹配
        if (p.charAt(pLen - 2) != '.' && p.charAt(pLen - 2) != s.charAt(sLen - 1)) {
            return isMatch(s, p.substring(0, pLen - 2));
        }
        // 匹配的话
        // s最后一位不消耗
        return isMatch(s, p.substring(0, pLen - 2))
                // 消耗
                || isMatch(s.substring(0, sLen - 1), p);
    }


}

class Solution1 implements Question {

    /**
     * @param s 被验证字符串
     * @param p 正则表达式
     * @return
     */
    // 动态规划解决
    @Override
    public boolean isMatch(String s, String p) {
        boolean[][] mem = new boolean[s.length() + 1][p.length() + 1];
        mem[0][0] = true;

        for (int i = 0; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j - 1) == '*') {
                    if (i == 0) {
                        mem[i][j] = mem[i][j - 2];
                    } else if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
                        mem[i][j] = mem[i][j - 2] || mem[i][j - 1] || mem[i - 1][j];
                    } else {
                        mem[i][j] = mem[i][j - 2];
                    }
                } else if (i > 0 && (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1))) {
                    mem[i][j] = mem[i - 1][j - 1];
                }
            }
        }

        return mem[s.length()][p.length()];
    }
}

class Solution2 implements Question {

    // 递归解决
    @Override
    public boolean isMatch(String s, String p) {
        // 递归终止条件
        if (s.length() == 0 && p.length() == 0) {
            return true;
        }
        // p空但是被验证字符串还有剩余 不匹配
        if (p.length() == 0) {
            return false;
        }
        if (s.length() == 0) {
            // 字符串用完了 正则表达式剩余字数为奇数 不匹配
            if (p.length() % 2 == 1) {
                return false;
            }
            // 字符串用完了 正则表达式偶数项不为* 不匹配
            for (int i = 1; i < p.length(); i += 2) {
                if (p.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }
        // p和s都不空 最后一位不匹配
        boolean isLastEqual = p.charAt(p.length() - 1) != s.charAt(s.length() - 1);
        if (p.charAt(p.length() - 1) != '*'
                && p.charAt(p.length() - 1) != '.'
                && isLastEqual) {
            return false;
        }

        if (p.charAt(p.length() - 1) == '*') {
            if (p.charAt(p.length() - 2) != '.' && p.charAt(p.length() - 2) != s.charAt(s.length() - 1)) {
                return isMatch(s, p.substring(0, p.length() - 2));
            }
            return isMatch(s, p.substring(0, p.length() - 2)) || isMatch(s.substring(0, s.length() - 1), p);
        } else {
            if (p.charAt(p.length() - 1) != '.' && isLastEqual) {
                return false;
            }
            return isMatch(s.substring(0, s.length() - 1), p.substring(0, p.length() - 1));
        }
    }
}

