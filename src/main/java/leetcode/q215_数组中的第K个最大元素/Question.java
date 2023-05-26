package leetcode.q215_数组中的第K个最大元素;



import java.util.PriorityQueue;
import java.util.Random;

public interface Question {
    int findKthLargest(int[] nums, int k);
}

class Test {
    public static void main(String[] args) {

    }
}

/**
 *      题解目录      时间复杂度 空间复杂度 描述
 * {@link Solution1}   O()       O()
 */
class Solution3 implements Question {

    @Override
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
        }
        for (int i = 0; i < nums.length - k; i++) {
            queue.poll();
        }
        return queue.element();
    }
}

class Solution2 implements Question {

    @Override
    public int findKthLargest(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        while (true) {
            int partition = partition(nums, left, right);
            if (partition < k - 1) {
                left = partition + 1;
            } else if (partition > k - 1) {
                right = partition - 1;
            } else {
                return nums[partition];
            }
        }
    }

    private int partition(int[] nums, int low, int high) {
        if (low == high) {
            return low;
        }
        Random random = new Random();
        swap(nums, low, random.nextInt(high - low) + low);
        int pivot = nums[low];
        int left = low, right = high + 1;
        while (left < right) {
            while (left < right && nums[--right] < pivot) {}
            while (left < right && nums[++left] > pivot) {}
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

class Solution1 implements Question {

    @Override
    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums, 0, nums.length - 1, k);
    }

    private int quickSort(int[] nums, int low, int high, int k) {
        if (low == high) {
            return nums[low];
        }
        int pivot = nums[high];
        int i = low;
        int j = high;
        while (i < j) {
            while (nums[i] >= pivot && i < j) {
                i++;
            }
            while (nums[j] <= pivot && i < j) {
                j--;
            }
            swap(nums, i, j);
        }
        swap(nums, i, high);
        if (i == k - 1) {
            return nums[i];
        }
        if (i < k - 1) {
            return quickSort(nums, i + 1, high, k);
        }
        return quickSort(nums, low, i - 1, k);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}