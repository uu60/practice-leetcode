package leetcode.q908_最小插值I;



import java.util.Arrays;

public interface Question {
    int smallestRangeI(int[] nums, int k);
}

class Test {
    public static void main(String[] args) {

    }
}

/**
 * 题解目录
 *                      时间击败 空间击败 时间复杂度 空间复杂度   描述
 * {@link Solution1}      23%    81%    O(logn)    O(1)     排序找
 * {@link Solution1}      74%    87%    O(n)       O(1)     一次遍历就能找到最大值和最小值
 */
class Solution2 implements Question {

    @Override
    public int smallestRangeI(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        return Math.max(max - min - 2 * k, 0);
    }
}

class Solution1 implements Question {

    @Override
    public int smallestRangeI(int[] nums, int k) {
        Arrays.sort(nums);
        int maxDelta = nums[nums.length - 1] - nums[0];
        return Math.max(maxDelta - 2 * k, 0);
    }
}