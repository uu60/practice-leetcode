package leetcode.q16_最接近的三数之和;



import java.util.Arrays;

public interface Question {
    int threeSumClosest(int[] nums, int target);

    class Test {
        public static void main(String[] args) {
            // Validator.exec(Question.class, 1, new int[]{1, 1, -1, -1, 3}, -1);
        }
    }
}

class Solution1 implements Question {

    @Override
    public int threeSumClosest(int[] nums, int target) {
        int res = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target)
                        < Math.abs(res - target)) {
                    if (sum == target)
                        return sum;
                    res = sum;
                }
                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}
