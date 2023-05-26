package leetcode.q27_移除元素;



public interface Question {
    int removeElement(int[] nums, int val);
}

class Test {
    public static void main(String[] args) {
        // Validator.exec(Question.class, 1, new int[]{1, 2, 3, 1, 3}, 1);
    }
}

class Solution1 implements Question {

    @Override
    public int removeElement(int[] nums, int val) {
        int tail = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[tail++] = nums[i];
            }
        }
        return tail;
    }
}
