package leetcode.q912_排序数组;



import java.util.Random;

public interface Question {
    int[] sortArray(int[] nums);
}

class Test {
    public static void main(String[] args) {
        
        
                

    }
}

/**
 *      题解目录      时间复杂度 空间复杂度 描述
 * {@link Solution1}    O()     O()
 */
class Solution1 implements Question {

    @Override
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int partition = partition(nums, low, high);
        quickSort(nums, low, partition - 1);
        quickSort(nums, partition + 1, high);
    }

    private int partition(int[] nums, int low, int high) {
        Random random = new Random();
        swap(nums, low, random.nextInt(high - low) + low);
        int pivot = nums[low];
        int left = low, right = high + 1;
        while (left < right) {
            while (left < right && nums[--right] > pivot) {}
            while (left < right && nums[++left] < pivot) {}
            swap(nums, left, right);
        }
        swap(nums, left, low);
        return left;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}