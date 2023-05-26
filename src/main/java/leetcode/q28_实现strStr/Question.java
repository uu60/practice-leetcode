package leetcode.q28_实现strStr;



public interface Question {
    int strStr(String haystack, String needle);
}

class Test {
    public static void main(String[] args) {

    }
}

/**
 * 题解目录
 * {@link Solution1}
 */
class Solution1 implements Question {

    @Override
    public int strStr(String haystack, String needle) {
        int nLen;
        int hLen = haystack.length();
        if (needle == null || (nLen = needle.length()) == 0) {
            return 0;
        }
        char first = needle.charAt(0);
        // a b c
        outer: for (int i = 0; i <= hLen - nLen; i++) {
            if (haystack.charAt(i) == first) {
                for (int j = 1; j < nLen; j++) {
                    if (haystack.charAt(i + j) != needle.charAt(j)) {
//                        i += j - 1;
                        continue outer;
                    }
                }
                return i;
            }
        }
        return -1;
    }
}