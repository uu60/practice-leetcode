package leetcode.q50_Pow_x_n;



public interface Question {
    double myPow(double x, int n);
}

class Test {
    public static void main(String[] args) {
        System.out.println(new Solution3().myPow(3, 11));
    }
}

class Solution3 implements Question {

    @Override
    public double myPow(double x, int n) {
        double res = 1;
        while (n != 0) {
            if (n % 2 == 1) {
                res *= x;
            }
            x = x * x;
            n = n >> 1;
        }
        return res;
    }
}

class Solution2 implements Question {

    @Override
    public double myPow(double x, int n) {
        double res = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                res *= x;
            }
            n = n >> 1;
            x *= x;
        }
        return res;
    }
}

class Solution1 implements Question {
    @Override
    public double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        boolean negative = n < 0;
        boolean isMin = false;
        if (n < 0) {
            if (n == Integer.MIN_VALUE) {
                n = Integer.MAX_VALUE;
                isMin = true;
            } else {
                n = -n;
            }
        }
        int temp = n;
        int len = 0;
        while (temp != 0) {
            temp /= 2;
            len++;
        }
        double[] dp = new double[len];
        boolean[] done = new boolean[len];
        double unprocessedRes = dfs(x, n, dp, done, len - 1);

        return negative ?
                (1 / unprocessedRes * (isMin ? 1 / x : 1))
                : unprocessedRes;
    }

    public double dfs(double x, int n, double[] dp,
                      boolean[] done, int pos) {
        if (done[pos]) {
            return dp[pos];
        }
        if (n == 1) {
            dp[0] = x;
            done[0] = true;
            return x;
        }
        int halfN = n / 2;
        boolean isOdd = n % 2 == 1;
        double res = dfs(x, halfN, dp, done, pos - 1)
                * dfs(x, halfN, dp, done, pos - 1)
                * (isOdd ? x : 1);
        dp[pos] = res;
        done[pos] = true;
        return res;
    }
}





