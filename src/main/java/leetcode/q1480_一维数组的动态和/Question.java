package leetcode.q1480_一维数组的动态和;



public interface Question {
    int[] runningSum(int[] nums);
}

class Test {
    public static void main(String[] args) {


    }
}

/**
 *      题解目录      时间击败 空间击败 时间复杂度 空间复杂度 描述
 * {@link Solution1}   100    15      O(N)       O(1)
 */
class Solution1 implements Question {

    @Override
    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }
}