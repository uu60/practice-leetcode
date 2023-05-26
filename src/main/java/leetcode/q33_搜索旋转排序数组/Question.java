package leetcode.q33_搜索旋转排序数组;

public interface Question {
    int search(int[] nums, int target);
}

class Test {
    public static void main(String[] args) {
        System.out.print(new Solution1().search(new int[]{3,4,5,6,7,8,1,2}, 2));
    }
}

/**
 * 题解目录      时间复杂度 空间复杂度 描述
 * {@link Solution1}    O()     O()
 */
class Solution1 implements Question {

    @Override
    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            return target == nums[0] ? 0 : -1;
        }
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] == target) {
                return mid;
            }
            // 左侧部分有序 包含mid
            if (mid > 0 && nums[low] <= nums[mid - 1] && target >= nums[low] && target <= nums[Math.max(low, mid - 1)]
            || (nums[mid + 1] <= nums[high] && (target < nums[mid + 1] || target > nums[high]))) {
                // 去左侧找
                high = mid - 1;
            } else { // 右侧部分有序
                low = mid + 1;
            }
        }
        return -1;
    }
}