package leetcode.q14_最长公共前缀;

public interface Question {
    String longestCommonPrefix(String[] strs);
}

class Test {
    public static void main(String[] args) {

    }
}

class Solution1 implements Question {

    @Override
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }
        int i;
        outer: for (i = 1; ; i++) {
            if (strs[0].length() < i) {
                break;
            }
            char ci = strs[0].charAt(i - 1);
            for (String str : strs) {
                if (str.length() == i - 1 || ci != str.charAt(i - 1)) {
                    break outer;
                }
            }
        }
        return strs[0].substring(0, i - 1);
    }
}