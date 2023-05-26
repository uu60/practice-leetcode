package leetcode.q42_接雨水;

/**
 * @author octopus
 * @since 2023/4/28 18:29
 */
public class Solution2 implements Question {

    /**
     * 双指针
     */
    @Override
    public int trap(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int leftMax = -1, rightMax = -1;
        while (left < right) {
            leftMax = Math.max(height[left], leftMax);
            rightMax = Math.max(height[right], rightMax);
            // 如果左指针低于右指针，leftMax <= rightMax
            if (height[left] <= height[right]) {
                ans += leftMax - height[left++];
            } else {
                ans += rightMax - height[right--];
            }
        }
        return ans;
    }
}
