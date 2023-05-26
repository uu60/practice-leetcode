package leetcode.q77_组合;

import java.util.ArrayList;
import java.util.List;

public interface Question {
    List<List<Integer>> combine(int n, int k);
}

class Test {
    public static void main(String[] args) {
        System.out.println(new Solution1().combine(20, 10));
    }
}

/**
 * 题解目录      时间复杂度 空间复杂度 描述
 * {@link Solution1}    O()     O()
 */
class Solution1 implements Question {

    @Override
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(k, n, new ArrayList<>(), ans);
        return ans;
    }

    private void backtrack(int k, int n, List<Integer> temp, List<List<Integer>> ans) {
        if (temp.size() == k) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        for (int i = temp.isEmpty() ? 1 : temp.get(temp.size() - 1) + 1; i <= n; i++) {
            temp.add(i);
            backtrack(k, n, temp, ans);
            temp.remove(temp.size() - 1);
        }
    }
}