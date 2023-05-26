package leetcode.q35_搜索插入位置;

public interface Question {
    int searchInsert(int[] nums, int target);
}

class Solution1 implements Question {

    @Override
    public int searchInsert(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    private static int binarySearch(int[] nums, int target, int low, int high) {
        if (low == high && target != nums[low]) {
            if (target > nums[low]) {
                return low + 1;
            } else {
                return low;
            }
        }
        int mid = (low + high) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return binarySearch(nums, target, low, mid == low ? mid : mid - 1);
        } else {
            return binarySearch(nums, target, mid + 1, high);
        }
    }

    public static void main(String[] args) {
        // Validator.exec(Question.class, 1, new int[]{1, 3}, 0);
    }
}
