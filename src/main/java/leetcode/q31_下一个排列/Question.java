package leetcode.q31_下一个排列;



import java.util.Arrays;

public interface Question {
    void nextPermutation(int[] nums);
}

class Test {
    public static void main(String[] args) {
        // Validator.exec(Question.class, 2, (Object) new int[]{3, 2, 1});
    }
}

class Solution2 implements Question {

    /**
     * 优化Solution1
     * @param nums
     */
    @Override
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        // 将i移动到倒序的前一个
        while (i > 0 && nums[i] <= nums[i - 1]) {
            i--;
        }
        if (i > 0) {
            int j = nums.length - 1;
            while (j > i && nums[j] <= nums[i - 1]) {
                j--;
            }
            swap(nums, j, i - 1);
        }
        for (int k = i; k <= (i + nums.length - 1) / 2; k++) {
            swap(nums, k, i + nums.length - 1 - k);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

class Solution1 implements Question {

    @Override
    public void nextPermutation(int[] nums) {
        for (int i = nums.length - 1; i > 0 ; i--) {
            if (nums[i] <= nums[i - 1]) {
                continue;
            }
            int j = i;
            while (j < nums.length && nums[j] > nums[i - 1]) {
                j++;
            }
            swap(nums, i - 1, j - 1);
            // 待优化，此处以及下面的sort根本不需要，既然是降序（从头到尾方向），直接反转即可
            Arrays.sort(nums, i, nums.length);
            return;
        }
        Arrays.sort(nums);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
