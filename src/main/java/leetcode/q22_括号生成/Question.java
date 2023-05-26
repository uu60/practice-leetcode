package leetcode.q22_括号生成;



import java.util.ArrayList;
import java.util.List;

public interface Question {
    List<String> generateParenthesis(int n);
}

class Test {
    public static void main(String[] args) {
        // Validator.benchmark(Question.class, new int[]{1, 2}, 5);
    }
}

class Solution2 implements Question {

    /**
     * 动态规划
     * dp[i] 表示i组括号的所有情况
     * @param n
     * @return
     */
    @Override
    public List<String> generateParenthesis(int n) {
        List<List<String>> dp = new ArrayList<>();
        dp.add(new ArrayList<>());
        dp.get(0).add("");
        for (int i = 1; i <= n; i++) {
            dp.add(new ArrayList<>());
            for (int p = 0; p < i; p++) {
                int q = i - p - 1;
                for (String ps : dp.get(p)) {
                    for (String qs : dp.get(q)) {
                        dp.get(i).add(String.format("(%s)%s", ps, qs));
                    }
                }
            }
        }
        return dp.get(n);
    }
}

class Solution1 implements Question {
    private List<String> res = new ArrayList<>();

    // 递归
    @Override
    public List<String> generateParenthesis(int n) {
        recurse("", n, n);
        return res;
    }

    /**
     * 当左括号多于右括号时才能够拼接右括号
     * 任何情况都可以拼接左括号
     *
     * @param s 当前字符串
     * @param left 剩余左括号
     * @param right 剩余右括号
     */
    private void recurse(String s, int left, int right) {
        if (left == 0 && right == 0) {
            // res是结果集合
            res.add(s);
            return;
        }
        if (left > 0)
            recurse(s + "(", left - 1, right);
        if (right > left) {
            recurse(s + ")", left, right - 1);
        }
    }

}
