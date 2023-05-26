package leetcode.q34_在排序数组中查找元素的第一个和最后一个位置_5;



public interface Question {
    int[] searchRange(int[] nums, int target);
}

/**
 *
 */
class Test {
    public static void main(String[] args) {

    }
}

/**
 * 题解目录
 * {@link Solution1} 尝试二分查找
 * {@link Solution2} 根据官方优化二分查找
 */

class Solution2 implements Question {

    @Override
    public int[] searchRange(int[] nums, int target) {
        int first = binarySearch(nums, target, true);
        int last = binarySearch(nums, target, false) - 1;
        if (first <= last && nums[first] == target && nums[last] == target) {
            return new int[]{first, last};
        }
//        binarySearch(new int[]{1, 2, 3, 4, 6}, 5, true);
        return new int[]{-1, -1};
    }

    // lower = true表示找到第一个大于等于target 否则是大于
    private static int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0;
        int right = nums.length - 1;
        int ans = nums.length;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target || (lower && nums[mid] == target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}

class Solution1 implements Question {

    /**
     * 二分查找后再找边界
     *
     * @param nums
     * @param target
     * @return
     */
    @Override
    public int[] searchRange(int[] nums, int target) {
        int i;
        if (nums.length == 0 || (i = binarySearch(nums, target)) == -1) {
            return new int[]{-1, -1};
        }
        int j = i;
        while (i >= 0 && nums[i] == target) {
            i--;
        }
        while (j < nums.length && nums[j] == target) {
            j++;
        }
        return new int[]{i + 1, j - 1};
    }

    private static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return nums[left] == target ? left : -1;
    }
}
