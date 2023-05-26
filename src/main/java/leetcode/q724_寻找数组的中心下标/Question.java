package leetcode.q724_寻找数组的中心下标;



public interface Question {
    int pivotIndex(int[] nums);
}

class Test {
    public static void main(String[] args) {
        
        
                

    }
}

/**
 *      题解目录      时间击败 空间击败 时间复杂度 空间复杂度 描述
 * {@link Solution1}                 O()       O()
 */
class Solution1 implements Question {

    @Override
    public int pivotIndex(int[] nums) {
        int[] leftSums = new int[nums.length];
        int[] rightSums = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            
        }
        return 0;
    }
}