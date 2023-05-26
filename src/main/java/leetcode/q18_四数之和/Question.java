package leetcode.q18_四数之和;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Question {
    List<List<Integer>> fourSum(int[] nums, int target);
}

class Test {
    public static void main(String[] args) {
        // Validator.exec(Question.class, 1, new int[]{-2,-1,-1,1,1,2,2}, 0);
    }
}

class
Solution1 implements Question {

    @Override
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 将数组排序
        Arrays.sort(nums);
        // 结果
        List<List<Integer>> res = new ArrayList<>();

        int len = nums.length;
        for (int i = 0; i < len - 3; i++) {
            // 首位和上一次循环相同，直接跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 从右侧找三个数和为target3
            int target3 = target - nums[i];
            for (int j = i + 1; j < len - 2; j++) {
                // 首位和上一次循环相同，直接跳过
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // 从第二个数右侧找两个数和为target2
                int target2 = target3 - nums[j];

                // 双指针寻找匹配的两个数
                int left = j + 1;
                int right = len - 1;
                while (left < right) {
                    int sum = nums[left] + nums[right];
                    if (sum < target2) {
                        left++;
                    } else if (sum > target2) {
                        right--;
                    } else {
                        res.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right])));
                        left++;
                        right--;
                        while (left < right && nums[left - 1] == nums[left]) {
                            left++;
                        }
                        while (left < right && nums[right + 1] == nums[right]) {
                            right--;
                        }
                    }
                }
            }
        }
        return res;
    }
}
