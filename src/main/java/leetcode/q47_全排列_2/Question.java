package leetcode.q47_全排列_2;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public interface Question {
    List<List<Integer>> permuteUnique(int[] nums);
}

class Test {
    public static void main(String[] args) {


    }
}

/**
 *      题解目录      时间击败 空间击败 时间复杂度 空间复杂度 描述
 * {@link Solution1}   21     77     O(n*n!)       O(n)   回溯法
 */
class Solution1 implements Question {

    @Override
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans, new ArrayList<>(), new ArrayList<>(Arrays.stream(nums).boxed().collect(Collectors.toList())));
        return ans;
    }

    private void dfs(List<List<Integer>> ans, List<Integer> cur, List<Integer> left) {
        if (left.isEmpty()) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        HashSet<Integer> heads = new HashSet<>();
        for (int i = 0; i < left.size(); i++) {
            if (heads.contains(left.get(i))) {
                continue;
            }
            heads.add(left.get(i));
            cur.add(left.remove(i));
            dfs(ans, cur, left);
            left.add(i, cur.remove(cur.size() - 1));
        }
    }
}