package leetcode.q15_三数之和;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public interface Question {
    List<List<Integer>> threeSum(int[] nums);
}

class Test {
    public static void main(String[] args) {
        int[] cs1 = {2, -3, 0, -2, -5, -5, -4, 1, 2, -2, 2, 0, 2, -4};
    }
}

class Solution2 implements Question {

    @Override
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] < -nums[i]) {
                    left++;
                } else if (nums[left] + nums[right] > -nums[i]) {
                    right--;
                } else {
                    ans.add(Arrays.asList(nums[i], nums[left++], nums[right--]));
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                }
            }
        }
        return ans;
    }
}


class Solution1 implements Question {

    @Override
    public List<List<Integer>> threeSum(int[] nums) {
        // 记录结果
        List<List<Integer>> res = new ArrayList<>();
        // 1 将数组排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                boolean lower = true, higher = true;
                if (nums[left] + nums[right] < target) {
                    left++;
                    higher = false;
                } else if (nums[left] + nums[right] > target) {
                    right--;
                    lower = false;
                } else { // 符合条件
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[left++], nums[right--])));
                }
                if (left >= right)
                    break;
                if (lower) {
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                }
                if (higher) {
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }

        return res;
    }
}
