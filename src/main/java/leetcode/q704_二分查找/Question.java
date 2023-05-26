package leetcode.q704_二分查找;

/**
 * 二分查找
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 */
public interface Question {
    int search(int[] nums, int target);
}

class Main {
    public static void main(String[] args) {
        // Validator.exec(Question.class, 1, new int[]{-1,0,3,5,9,12}, 9);
    }
}
