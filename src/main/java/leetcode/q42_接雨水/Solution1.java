package leetcode.q42_接雨水;

/**
 * @author octopus
 * @since 2023/4/28 18:17
 */
public class Solution1 implements Question {

    @Override
    public int trap(int[] height) {
        // 从左向右找到每个位置左侧最高的数
        int[] leftHighest = new int[height.length];
        int highest = -1;
        for (int i = 0; i < height.length; i++) {
            highest = Math.max(highest, height[i]);
            leftHighest[i] = highest;
        }

        // 从右向左找到每个位置右侧最高的数
        int[] rightHighest = new int[height.length];
        highest = -1;
        for (int i = height.length - 1; i >= 0; i--) {
            highest = Math.max(highest, height[i]);
            rightHighest[i] = highest;
        }

        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            ans += Math.min(leftHighest[i], rightHighest[i]) - height[i];
        }

        return ans;
    }
}
