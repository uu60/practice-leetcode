package leetcode.qn;

import structure.TreeNode;

import java.util.*;

public interface Question {

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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        int level = 0;

        Queue<TreeNode> queue = new LinkedList<>() ;
        queue.add(root);
        while (!queue.isEmpty())
        {
            int n = queue.size();
            List<Integer> path = new ArrayList<>();
            while (n-- > 0)
            {
                TreeNode node = queue.poll();
                path.add(node.val);

                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }

            level++;
            if(level % 2 == 0)
            {
                Collections.reverse(path);
            }
            res.add(path);

        }
        return res;
    }
}