package leetcode.q38_外观数列;



public interface Question {
    String countAndSay(int n);
}

class Test {
    public static void main(String[] args) {

    }
}

/**
 * 题解目录
 * {@link Solution1} 迭代
 * {@link Solution1} 递归
 */
class Solution2 implements Question {

    @Override
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        return descsribe(countAndSay(n - 1));
    }

    private String descsribe(String str) {
        int i = 0;
        StringBuilder temp = new StringBuilder();
        while (i < str.length()) {
            int num = 1;
            char curC = str.charAt(i++);
            while (i < str.length() && str.charAt(i) == curC) {
                num++;
                i++;
            }
            temp.append(num).append(curC);
        }
        return temp.toString();
    }
}

class Solution1 implements Question {

    @Override
    public String countAndSay(int n) {
        String last = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder temp = new StringBuilder();
            int length = last.length();
            int j = 0;
            while (j < length) {
                int num = 1;
                char curC = last.charAt(j++);
                while (j < last.length() && last.charAt(j) == curC) {
                    num++;
                    j++;
                }
                temp.append(num).append(curC);
            }
            last = temp.toString();
        }
        return last;
    }
}