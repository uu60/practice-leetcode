package leetcode.q11_盛最多水的容器;

public interface Question {
    int maxArea(int[] height);

    static void main(String[] args) {
        // Validator.exec(Question.class, 0, new int[]{1, 3, 5, 2, 7});
    }
}

class Solution1 implements Question {
    /**
     * 遍历
     * 超出时间限制
     * @param height
     * @return 水面积
     */
    @Override
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return max;
    }
}

class Solution0 implements Question {

    /**
     * 官方 双指针
     * 首先指向数组的两端，移动较小的那个
     * @param height
     * @return
     */
    @Override
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int max = 0;
        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}