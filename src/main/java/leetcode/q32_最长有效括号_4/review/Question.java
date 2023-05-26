package leetcode.q32_最长有效括号_4.review;



import java.util.Stack;

public interface Question {
    int longestValidParentheses(String s);
}

class Test {
    public static void main(String[] args) {
        String case1 = "))))((()((";
        String case2 = "())";
        String case3 = ")()())";
        String case4 = "(()";

    }
}


class Solution2 implements Question {

    // 练习栈
    // 尝试不在栈底额外保存一个元素
    @Override
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                }
                res = Math.max(res, i - stack.peek());
            }
        }
        return res;
    }
}

class Solution1 implements Question {

    /**
     * 练习动态规划
     *
     * @param s
     * @return
     */
    @Override
    public int longestValidParentheses(String s) {
        // dp[i]表示以s[i]结尾的合规子串
        int[] dp = new int[s.length()];
        int res = 0;

        for (int i = 1; i < s.length(); i++) {
            // 左括号结尾不可能有合规子串，只处理右括号
            if (s.charAt(i) == ')') {
                // 上一个是左括号的话
                // ()()  ()
                if (s.charAt(i - 1) == '(') {
                    dp[i] = 2 + (i > 1 ? dp[i - 2] : 0);
                } else if (s.charAt(i - dp[i - 1] - 1) == '(') { // 上一个也是右括号并且他之前合规括号组合前一个是左括号
                    // (())
                    dp[i] = 2 + dp[i - 1] + (i - dp[i - 1] > 2 ? dp[i - dp[i - 1] - 2] : 0);
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }
}
