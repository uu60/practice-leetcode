package leetcode.q278_first_error;



public class Solution1 implements Question {
    private static final int ERROR_FIRST = 1702766719;

    @Override
    public int firstBadVersion(int n) {
        int low = 0, high = n;
        while (low <= high) {
            int mid = (int) (((long) low + high) / 2);
            // 如果中间错了 证明第一错位在其前
            if (isBadVersion(mid)) {
                if (!isBadVersion(mid - 1)) {
                    return mid;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return 0;
    }

    private static boolean isBadVersion(long n) {
        return n >= ERROR_FIRST;
    }

    public static void main(String[] args) {
        // Validator.exec(Question.class, 1, 2126753390);
    }
}
