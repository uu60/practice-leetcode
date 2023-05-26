package leetcode.q1_两数之和;



import java.util.HashMap;
import java.util.Map;

public interface Question {
    int[] twoSum(int[] nums, int target);
}

class Test {
    public static void main(String[] args) {

    }
}

/**
 * 题解目录
 * {@link Solution1}
 */
class Solution1 implements Question {

    @Override
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // 有匹配
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            // 没有匹配
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
