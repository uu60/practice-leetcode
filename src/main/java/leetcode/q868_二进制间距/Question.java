package leetcode.q868_二进制间距;



public interface Question {
    int binaryGap(int n);
}

class Test {
    public static void main(String[] args) {

    }
}

/**
 * 题解目录
 * {@link Solution1} 十进制转二进制后再判断 击败33-53
 * {@link Solution2} 尝试官方数字移位操作 击败100-32
 * {@link Solution3} 复现官方数字移位操作 击败100-81
 *
 */
class Solution3 implements Question {

    @Override
    public int binaryGap(int n) {
        int last = -1, res = 0;
        for (int i = 0; n > 0; i++) {
            if ((n & 1) == 1) {
                if (last != -1) {
                    res = Math.max(res, i - last);
                }
                last = i;
            }
            n >>= 1;
        }
        return res;
    }
}

class Solution2 implements Question {

    @Override
    public int binaryGap(int n) {
        int res = 0;
        while (n > 0) {
            while (n % 2 == 0) {
                n = n >> 1;
            }
            int cur = 1;
            // 现在最后一位是1
            n = n >> 1;
            while (n > 0 && n % 2 == 0) {
                n = n >> 1;
                cur++;
            }
            if (n > 0) {
                res = Math.max(res, cur);
            }
        }
        return res;
    }
}

class Solution1 implements Question {

    @Override
    public int binaryGap(int n) {
        int res = 0;
        String num = transfer(n);
        for (int left = 0; left < num.length() - 1; left++) {
            if (num.charAt(left) == '1') {
                for (int right = left + 1; right < num.length(); right++) {
                    if (num.charAt(right) == '1') {
                        res = Math.max(res, right - left);
                        left = right - 1; // left进入下次循环会到right这里
                        break;
                    }
                }
            }
        }

        return res;
    }

    // 十进制转二进制
    private String transfer(int n) {
        StringBuilder res = new StringBuilder();
        while (n > 0) {
            res.insert(0, n % 2);
            n /= 2;
        }
        return res.toString();
    }
}