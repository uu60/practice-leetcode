package leetcode.q236_二叉树的最近公共祖先;

import structure.TreeNode;

import java.util.*;

public interface Question {
    TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q);
}

class Test {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(5);
        TreeNode t3 = new TreeNode(1);
        TreeNode t4 = new TreeNode(6);
        TreeNode t5 = new TreeNode(2);
        TreeNode t6 = new TreeNode(0);
        TreeNode t7 = new TreeNode(8);
        TreeNode t8 = new TreeNode(7);
        TreeNode t9 = new TreeNode(4);

        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;
        t5.left = t8;
        t5.right = t9;
//        new Solution3().lowestCommonAncestor(t1, t2, t3);
        System.out.println(new Solution6().lowestCommonAncestor(t1, t8, t6));
    }
}
/*
                3
               /   \
              5     1
             /  \   /  \
            6   2   0  8
               /  \
              7    4
         */
class Solution6 implements Question {

    @Override
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

}

class Solution5 implements Question {

    TreeNode ans;

    @Override
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }

    private boolean dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return false;
        }
        boolean lson = dfs(node.left, p, q);
        boolean rson = dfs(node.right, p, q);
        if (lson && rson || ((lson || rson) && (node == p || node == q))) {
            this.ans = node;
        }
        return lson || rson || node == p || node == q;
    }
}

class Solution4 implements Question {

    @Override
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, TreeNode> map = new HashMap<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.left != null) {
                queue.offer(poll.left);
                map.put(poll.left, poll);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
                map.put(poll.right, poll);
            }
        }

        Set<TreeNode> pParents = new HashSet<>();
        for (TreeNode ptr = p; ptr != null; ptr = map.get(ptr)) {
            pParents.add(ptr);
        }
        for (TreeNode ptr = q; ptr != null; ptr = map.get(ptr)) {
            if (pParents.contains(ptr)) {
                return ptr;
            }
        }
        return null;
    }
}

/**
 * 题解目录      时间复杂度 空间复杂度 描述
 * {@link Solution1}    O()     O()
 */
class Solution3 implements Question {

    @Override
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        HashMap<TreeNode, TreeNode> parents = new HashMap<>();
        recordParents(parents, root);

        HashSet<TreeNode> traversed = new HashSet<>();
        TreeNode t = p;
        while (t != null) {
            traversed.add(t);
            t = parents.get(t);
        }
        t = q;
        while (!traversed.contains(t)) {
            t = parents.get(t);
        }
        return t;
    }

    private TreeNode recordParents(HashMap<TreeNode, TreeNode> parents, TreeNode root) {
        if (root.left != null) {
            parents.put(recordParents(parents, root.left), root);
        }
        if (root.right != null) {
            parents.put(recordParents(parents, root.right), root);
        }
        return root;
    }
}

class Solution2 implements Question {

    private TreeNode ans;

    @Override
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return ans;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || this.ans != null) {
            return false;
        }
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            this.ans = root;
        }
        return lson || rson || root.val == p.val || root.val == q.val;
    }
}

class Solution1 implements Question {

    private TreeNode ans;

    @Override
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || (root.val == p.val || root.val == q.val && (lson || rson))) {
            ans = root;
        }
        return lson || rson || root.val == p.val || root.val == q.val;
    }
}