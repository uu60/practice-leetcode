package leetcode.q26_删除有序数组中的重复项;



public interface Question {
    int removeDuplicates(int[] nums);
}

class Test {
    public static void main(String[] args) {
        // Validator.exec(Question.class, 1, new int[]{0,0,1,1,1,2,2,3,3,4});
    }
}

class Solution1 implements Question {

    @Override
    public int removeDuplicates(int[] nums) {
        int tail = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[tail]) {
                nums[++tail] = nums[i];
            }
        }
        return tail + 1;
    }
}
