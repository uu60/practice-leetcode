package leetcode.q4_寻找两个正序数组的中位数;



public interface Question {
    double findMedianSortedArrays(int[] nums1, int[] nums2);
}

class Test {
    public static void main(String[] args) {

    }
}

/**
 * 题解目录             时间击败 空间击败 时间复杂度 空间复杂度 描述
 * {@link Solution1}                                    past
 * {@link Solution2}  100   47      O(n)      O(1)      双指针for写法（题解）
 * {@link Solution3}  100   29      O(n)      O(1)      双指针while写法
 * {@link Solution4}  100   60      O(n)      O(n)      归并排序
 */
class Solution4 implements Question {

    @Override
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int len = m + n;
        int[] all = new int[m + n];
        int p1 = 0, p2 = 0;
        for (int i = 0; i < len; i++) {
            all[i] = (p1 < m && (p2 >= n || nums1[p1] < nums2[p2])) ? nums1[p1++] : nums2[p2++];
        }
        return (len & 1) == 0 ? (all[len / 2] + all[len / 2 - 1]) / 2.0 : all[len / 2];
    }
}

class Solution3 implements Question {

    @Override
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;

        int p1 = 0, p2 = 0;
        boolean on1 = true;
        int prevMidValue = 0;

        while (p1 + p2 <= len / 2) {
            if (p1 < len1 && (p2 >= len2 || nums1[p1] < nums2[p2])) {
                p1++;
                on1 = true;
            } else {
                p2++;
                on1 = false;
            }
            if (p1 + p2 == len / 2) {
                prevMidValue = on1 ? nums1[p1 - 1] : nums2[p2 - 1];
            }
        }

        int midValue = on1 ? nums1[p1 - 1] : nums2[p2 - 1];

        return (len & 1) == 0 ? (midValue + prevMidValue) / 2.0 : midValue;
    }
}

class Solution2 implements Question {

    @Override
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        // left是中间位前一位的值
        int left = -1, right = -1;
        int p1 = 0, p2 = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (p1 < m && (p2 >= n || nums1[p1] < nums2[p2])) {
                right = nums1[p1++];
            } else {
                right = nums2[p2++];
            }
        }
        return (len & 1) == 0 ? (left + right) / 2.0 : right;
    }
}

class Solution1 implements Question {

    @Override
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLen = nums1.length + nums2.length;
        int mid = totalLen / 2 + totalLen % 2;
        if (nums1.length == 0 || nums2.length == 0) {
            if (totalLen % 2 == 1) {
                return nums1.length == 0 ? nums2[mid - 1] : nums1[mid - 1];
            }
            return (nums1.length == 0 ? nums2[mid - 1] + nums2[mid] : nums1[mid - 1] + nums1[mid]) / 2.0;
        }
        // i指向num1未遍历第一个索引, j同理
        int i = 0, j = 0;
        // current = 0表示当前在num1上
        int current = 0;
        // total表示current移动总次数
        int total = 0;
        int[] res = new int[2];
        while (total <= mid) {
            if (i < nums1.length && (j == nums2.length || nums1[i] <= nums2[j])) {
                current = 0;
                i++;
            } else {
                current = 1;
                j++;
            }
            total++;
            int currentVal = current == 0 ? nums1[i - 1] : nums2[j - 1];
            if (total > mid) {
                res[1] = currentVal;
            }
            if (total == mid) {
                if (totalLen % 2 == 1) {
                    return currentVal;
                }
                res[0] = currentVal;
            }
        }
        return (res[0] + res[1]) / 2.0;
    }
}
