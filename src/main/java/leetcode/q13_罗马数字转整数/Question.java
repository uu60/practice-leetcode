package leetcode.q13_罗马数字转整数;



public interface Question {
    int romanToInt(String s);
}

class Test {
    public static void main(String[] args) {

    }
}

/**
 *      题解目录      时间击败 空间击败 时间复杂度 空间复杂度 描述
 * {@link Solution1}                 O()       O()
 */
class Solution1 implements Question {

    @Override
    public int romanToInt(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'V':
                    ans += 5;
                    break;
                case 'L':
                    ans += 50;
                    break;
                case 'D':
                    ans += 500;
                    break;
                case 'M':
                    ans += 1000;
                    break;
            }
        }
        return 0;
    }
}