package leetcode.q46_全排列;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface Question {
    List<List<Integer>> permute(int[] nums);
}

class Test {
    public static void main(String[] args) {

    }
}

/**
 * 题解目录
 * {@link Solution1} 错误的，缺少情况
 * {@link Solution2} 递归解决，回溯 击败9-30 -_-
 * {@link Solution3} 从长度为0一直开始往里填 击败9-20
 * {@link Solution4} 尝试实现官方
 */
class Solution4 implements Question {

    @Override
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = Arrays.stream(nums).boxed().collect(Collectors.toList());
        if (nums.length <= 1) {
            res.add(temp);
            return res;
        }
        backtrack(nums.length, 0, temp, res);
        return res;
    }

    /**
     *
     * @param n 传入数组的长度，用于判断first情况
     * @param first 当前被交换的位置
     * @param temp 临时结果记录
     * @param res 最终结果
     */
    private void backtrack(int n, int first, List<Integer> temp, List<List<Integer>> res) {
        if (first == n - 1) {
            res.add(new ArrayList<>(temp));
        }
        for (int i = 0; i < n - first; i++) {
            Collections.swap(temp, first, first + i);
            backtrack(n, first + 1, temp, res);
            Collections.swap(temp, first, first + i);
        }
    }
}

class Solution3 implements Question {

    @Override
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length <= 1) {
            res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return res;
        }
        res.add(new ArrayList<>(Collections.singletonList(nums[0])));
        // nums.length次循环即可添加成所有的结果
        for (int i = 1; i < nums.length; i++) {
            // 对结果集里的所有元素插值
            int size = res.size();
            for (int j = 0; j < size; j++) {
                List<Integer> member = res.remove(0);
                // 对每个位置插一次输入数组中第i个数
                for (int k = 0; k < i + 1; k++) {
                    List<Integer> temp;
                    if (k == i) {
                        temp = member;
                    } else {
                        temp = new ArrayList<>();
                        temp.addAll(member);
                    }
                    temp.add(k, nums[i]);
                    res.add(temp);
                }
            }
        }
        return res;
    }
}

class Solution2 implements Question {

    /**
     * 回溯法
     * 击败9-30
     * @param nums
     * @return
     */
    @Override
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(new ArrayList<>(), Arrays.stream(nums).boxed().collect(Collectors.toList()), res);
        return res;
    }

    private void backtrack(List<Integer> lastRes, List<Integer> leftNums, List<List<Integer>> finalRes) {
        if (leftNums.size() == 1) {
            List<Integer> temp = new ArrayList<>();
            temp.addAll(lastRes);
            temp.addAll(leftNums);
            finalRes.add(temp);
            return;
        }
        for (int i = 0; i < leftNums.size(); i++) {
//            List<Integer> tempLeftNums = new ArrayList<>(leftNums);
//            tempLeftNums.remove(i);
            Integer add = leftNums.get(i);
            lastRes.add(add);
            Integer remove = leftNums.remove(i);
//            List<Integer> tempLastRes = new ArrayList<>(lastRes);
//            tempLastRes.add(leftNums.get(i));
            leftNums.remove(remove);
            backtrack(lastRes, leftNums, finalRes);
            lastRes.remove(add);
            leftNums.add(i, remove);
        }
    }
}
class Solution1 implements Question {

    /**
     * 借助冒泡排序
     * 错误：缺少情况
     * @param nums
     * @return
     */
    @Override
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        int length = nums.length;
        if (length <= 1) {
            res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return res;
        }
        int[] bubbles = new int[length];
        for (int i = 0; i < length; i++) {
            bubbles[i] = i;
        }

        // 开始冒泡
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - 1; j++) {
                swap(bubbles, j, j + 1);
                List<Integer> temp = new ArrayList<>();
                for (int k = 0; k < length; k++) {
                    temp.add(nums[bubbles[k]]);
                }
                res.add(temp);
            }
        }
        return res;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}