package leetcode.q39_组合总和;



import java.util.ArrayList;
import java.util.List;

public interface Question {
    List<List<Integer>> combinationSum(int[] candidates, int target);
}

class Test {
    public static void main(String[] args) {

    }
}

/**
 * 题解目录
 * {@link Solution1}正向回溯法
 * {@link Solution2}优化s1，改变target，不用每次重新求和
 * {@link Solution3}尝试官方倒向回溯法
 */
class Solution3 implements Question {

    @Override
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] candidates, int begin, int target, List<Integer> current, List<List<Integer>> res) {
        if (begin == candidates.length) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(current));
            return;
        }
        // 不使用当前数
        dfs(candidates, begin + 1, target, current, res);
        // 使用当前数
        if (candidates[begin] <= target) {
            current.add(candidates[begin]);
            dfs(candidates, begin, target - candidates[begin], current, res);
            current.remove(current.size() - 1);
        }
    }
}

class Solution2 implements Question {

    @Override
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] candidates, int begin, int target, List<Integer> current, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        // 如果当前位加上之前的满足就添加到结果
        if (target == 0) {
            res.add(new ArrayList<>(current));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            current.add(candidates[i]);
            backtrack(candidates, i, target - candidates[i], current, res);
            current.remove(current.size() - 1);
        }
    }
}

class Solution1 implements Question {

    @Override
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] candidates, int begin, int target, List<Integer> current, List<List<Integer>> res) {
        if (sum(current) > target) {
            return;
        }
        // 如果当前位加上之前的满足就添加到结果
        if (sum(current) == target) {
            res.add(new ArrayList<>(current));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            current.add(candidates[i]);
            backtrack(candidates, i, target, current, res);
            current.remove(current.size() - 1);
        }
    }

    private int sum(List<Integer> list) {
        return list.stream().mapToInt(value -> value).sum();
    }
}