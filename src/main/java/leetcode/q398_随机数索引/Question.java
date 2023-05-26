package leetcode.q398_随机数索引;



import java.util.*;

public interface Question {
    int pick(int target);
}

class Test {
    public static void main(String[] args) {

    }
}

/**
 * 题解目录
 * {@link Solution1} 保存所有情况再随机输出 击败5-17
 * {@link Solution2} 边遍历边统计 击败82-80
 * {@link Solution3} 水塘抽样 击败82-69
 */
class Solution3 implements Question {

    private int[] nums;
    private Random random;
    public Solution3(int[] nums) {
        this.nums = nums;
        this.random = new Random();
    }
    @Override
    public int pick(int target) {
        int res = 0, cur = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                cur++;
                if (random.nextInt(cur) == 0) {
                    res = i;
                }
            }
        }
        return res;
    }
}

class Solution2 implements Question {

    private int[] nums = null;
    public Solution2(int[] nums) {
        this.nums = nums;
    }
    @Override
    public int pick(int target) {
        List<Integer> idxes = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                idxes.add(i);
            }
        }
        return idxes.get(new Random().nextInt(idxes.size()));
    }
}

class Solution1 implements Question {

    Map<Integer, List<Integer>> map = new HashMap<>();
    public Solution1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new ArrayList<>(Collections.singletonList(i)));
            } else {
                map.get(nums[i]).add(i);
            }
        }
    }

    @Override
    public int pick(int target) {
        List<Integer> idxes = map.get(target);
        int i = new Random().nextInt(idxes.size());
        return idxes.get(i);
    }
}