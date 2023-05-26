package leetcode.q8_字符串转换整数atoi;

public interface Question {
    int myAtoi(String s);

    public static void main(String[] args) {
    }
}

class Solution1 implements Question {

    @Override
    public int myAtoi(String s) {
        int res = 0;
        int factor = 1;
        char[] chars = s.toCharArray();

        int i;
        for (i = 0; i < chars.length; i++) {
            // 空格先跳过
            if (chars[i] != ' ') {
                factor = chars[i] == '-' ? -1 : 1;
                if (chars[i] == '+' || chars[i] == '-') {
                    i++;
                }
                break;
            }
        }
        for (; i < chars.length; i++) {
            // 遇到非数字结束
            if (!Character.isDigit(chars[i])) {
                break;
            }
            // +数 and 超出范围
            if (res > Integer.MAX_VALUE / 10
                    || (res == Integer.MAX_VALUE / 10 && chars[i] > '7')) {
                return factor == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res *= 10;
            res += Integer.parseInt(chars[i] + "");
        }
        return res * factor;
    }
}