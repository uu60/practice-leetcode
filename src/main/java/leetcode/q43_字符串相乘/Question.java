package leetcode.q43_字符串相乘;



import java.util.ArrayList;
import java.util.List;

public interface Question {
    String multiply(String num1, String num2);
}

class Test {
    public static void main(String[] args) {

    }
}

/**
 * 题解目录           时间击败 空间击败 时间复杂度 空间复杂度 描述
 * {@link Solution1} 9%      5%        O(n)     O(n)   模拟竖式
 * {@link Solution2} 9%      5%        O(n)     O(n)   用数组保存结果
 */
class Solution2 implements Question {

    @Override
    public String multiply(String num1, String num2) {
        return null;
    }
}

class Solution1 implements Question {

    @Override
    public String multiply(String num1, String num2) {
        List<String> es = new ArrayList<>();
        // num1放在竖式下面
        int len1 = num1.length();
        int len2 = num2.length();
        for (int i = 0; i < len1; i++) {
            StringBuilder cur = new StringBuilder();
            for (int j = 0; j < i; j++) {
                cur.append(0);
            }
            int carry = 0;
            for (int j = 0; j < len2; j++) {
                int temp = (num2.charAt(len2 - j - 1) - '0') * (num1.charAt(len1 - i - 1) - '0') + carry;
                carry = temp / 10;
                temp %= 10;
                cur.insert(0, temp);
            }
            if (carry != 0) {
                cur.insert(0, carry);
            }
            es.add(cur.toString());
        }
        String res = es.stream().reduce(this::plus).get();
        StringBuilder last = new StringBuilder(res);
        while (last.charAt(0) == '0' && last.length() > 1) {
            last.deleteCharAt(0);
        }
        return last.toString();
    }

    private String plus(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int i = 0;
        int carry = 0;
        int len1 = num1.length();
        int len2 = num2.length();
        while (len1 > i || len2 > i) {
            char c1 = i >= len1 ? '0' : num1.charAt(len1 - i - 1);
            char c2 = i >= len2 ? '0' : num2.charAt(len2 - i - 1);
            int cur = c1 + c2 - '0' * 2 + carry;
            if (cur >= 10) {
                carry = 1;
                cur %= 10;
            } else {
                carry = 0;
            }
            res.insert(0, cur);
            i++;
        }
        if (carry == 1) {
            res.insert(0, 1);
        }
        return res.toString();
    }
}