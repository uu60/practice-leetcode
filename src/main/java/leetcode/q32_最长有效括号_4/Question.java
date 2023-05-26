package leetcode.q32_最长有效括号_4;

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


        // Validator.exec(Question.class, 6, case3);
        // Validator.benchmark(Question.class, new int[]{1, 2, 3, 4, 5, 6}, case1);
//        Validator validator = new Validator(Question.class);

    }
}

/**
 * 题解目录
 * {@link Solution1}暴力
 * {@link Solution2}复现官方动态规划
 * {@link Solution3}改进Solution2
 * {@link Solution4}复现官方栈方法
 * {@link Solution5}改进Solution4
 * {@link Solution6}复现官方不使用额外空间
 */

class Solution6 implements Question {

    /**
     * 不使用额外空间
     *
     * @param s
     * @return
     */
    @Override
    public int longestValidParentheses(String s) {
        int left = 0, right = 0; // 记录左右括号数
        int res = 0;
        // 先从左到右
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                res = Math.max(res, left * 2);
            } else if (left < right) {
                left = 0;
                right = 0;
            }
        }
        left = 0;
        right = 0;
        // 再从右到左
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                res = Math.max(res, left * 2);
            } else if (right < left) {
                left = 0;
                right = 0;
            }
        }
        return res;
    }
}

class Solution5 implements Question {
    /**
     * 改进
     * 24 - 60
     *
     * @param s
     * @return
     */
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
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }
}

class Solution4 implements Question {

    /**
     * 复现官方栈方法
     * both击败15%左右
     *
     * @param s
     * @return
     */
    @Override
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') { // 左括号就压栈
                stack.push(i);
            } else { // 右括号就弹出来，完成一次匹配后更新答案
                Integer pop = stack.pop();
                char c = (pop == -1 ? ')' : s.charAt(pop));
                if (c == '(') // 如果是左括号，更新最大值
                    res = Math.max(res, i - stack.peek());
                if (stack.isEmpty()) {
                    // 如果空了，说明之前的匹配完了，当前这个')'不能匹配，作为下一轮开头的前一个
                    stack.push(i);
                }
            }
        }
        return res;
    }
}

class Solution3 implements Question {

    /**
     * 官方解法1
     * 动态规划
     *
     * @param s
     * @return
     */
    @Override
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') { // 只有遇到')'才操作
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 // 前面符合要求的括号是否顶到了开头，
                        // 是的话当前')'肯定没有正确的匹配，如(()))
                        && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2 +// 不能只加2 有可能是这种情况 ()( ()(()) )
                            (i - dp[i - 1] < 2 ? // 判断匹配的'('之前剩余是否不少于2（这样前面的才有可能匹配）
                                    0 : dp[i - dp[i - 1] - 2]);
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }
}

class Solution2 implements Question {

    /**
     * 语法过于复杂
     * <p>
     * 复现官方动态规划
     * dp[i]表示以i字符结尾的最长有效子串
     * 分析：
     * i如果是'('，直接0
     * i如果是')'
     * 如果i-1是'('，则dp[i] = dp[i - 2] + 2
     * 如果i-1是')'，则跳到i - dp[i-1] - 1看
     * 如果是'('，则dp[i] = dp[i-1] + 2
     * 如果是')'，重复上述操作
     *
     * @param s
     * @return
     */
    @Override
    public int longestValidParentheses(String s) {
        int res = 0;
        int[] dp = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                dp[i] = 0; // 这是废话
            } else { // s[i] == ')'
                if (i > 1 && s.charAt(i - 1) == '(') { // ()
                    dp[i] = dp[i - 2] + 2;
                } else { // ))
                    // 下面的代码已经不是动态规划的精神了
                    int idx = i; // 前面可能与s[i]匹配的指针
                    while (idx > 0 && (idx = idx - dp[idx - 1] - 1) >= 0) { // (()())
                        if (s.charAt(idx) == '(') {
                            dp[i] = i - idx + 1 + (idx == 0 ? 0 : dp[idx - 1]);
                            break;
                        }
                    }
                }
                if (res < dp[i])
                    res = dp[i];
            }
        }
        return res;
    }
}

class Solution1 implements Question {

    /**
     * 暴力解决一下
     * 超出时间限制 -_-
     *
     * @param s
     * @return
     */
    @Override
    public int longestValidParentheses(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + res - 1; j < s.length(); j++) {
                if (isValid(s.substring(i, j + 1))) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }

    private static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else if (stack.isEmpty() || stack.pop() != '(') {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
