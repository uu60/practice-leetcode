package leetcode.q45_跳跃游戏_2;



public interface Question {
    int jump(int[] nums);
}

class Test {
    public static void main(String[] args) {

    }
}

/**
 * 题解目录      时间击败 空间击败 时间复杂度 空间复杂度 描述
 * {@link Solution1}    !       !      !       !      深度优先搜索 回溯法 超出时间限制
 * {@link Solution2}    22     12      O(n2)    O(1)  贪心从后向前
 * {@link Solution3}    41     41      O(n2)    O(1)  贪心从前向后
 * {@link Solution4}    99     12      O(n2)    O(1)  官方正向贪心
 */
class Solution4 implements Question {

    @Override
    public int jump(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int ans = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (end == i) {
                ans++;
                end = maxPosition;
            }
        }
        return ans;
    }
}

class Solution3 implements Question {

    @Override
    public int jump(int[] nums) {
        int ans = 0;
        int pos = 0;
        while (pos < nums.length - 1) {
            int nextPos = pos + 1;
            for (int i = 1; i <= nums[pos]; i++) {
                if (pos + i >= nums.length - 1) {
                    return ++ans;
                }
                nextPos = nextPos + nums[nextPos] > pos + i + nums[pos + i] ? nextPos : pos + i;
            }
            pos = nextPos;
            ans++;
        }
        return ans;
    }
}

class Solution2 implements Question {

    @Override
    public int jump(int[] nums) {
        int ans = 0;
        int pos = nums.length - 1;
        while (pos != 0) {
            for (int i = 0; i < pos; i++) {
                if (nums[i] + i >= pos) {
                    pos = i;
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }
}

class Solution1 implements Question {

    private int ans = Integer.MAX_VALUE;

    @Override
    public int jump(int[] nums) {
        dfs(nums, 0, 0);
        return ans;
    }

    private void dfs(int[] nums, int start, int timeNum) {
        if (start == nums.length - 1) {
            ans = Math.min(ans, timeNum);
        }
        if (start >= nums.length || nums[start] == 0) {
            return;
        }
        for (int i = 1; i <= nums[start]; i++) {
            dfs(nums, start + i, timeNum + 1);
        }
    }
}