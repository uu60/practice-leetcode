package structure;

import java.util.*;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(Integer[] list) {
        // assert list != null && list.length > 0 : "empty node list.";
        if (list.length == 0) {
            return;
        }
        Map<Integer, TreeNode> map = new HashMap<>();
        this.val = list[0];
        map.put(0, this);
        for (int i = 1; i < list.length; i++) {
            if (list[i] != null) {
                TreeNode parent = map.get(i / 2 - (i % 2 == 0 ? 1 : 0));
                if (parent == null) throw new RuntimeException("Illegal tree because child has a null parent.");
                TreeNode node = new TreeNode(list[i]);
                map.put(i, node);
                // left child
                if (i % 2 != 0) parent.left = node;
                else parent.right = node;
            }
        }
    }

    public static List<Integer> preorderTraverse(TreeNode root, boolean recursive) {
        List<Integer> res = new ArrayList<>();
        if (!recursive) {
            Stack<TreeNode> stack = new Stack<>();
            while (root != null || !stack.isEmpty()) {
                if (root != null) {
                    res.add(root.val);
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop().right;
                }
            }
        } else {
            preorderRecurse(root, res);
        }
        return res;
    }

    private static void preorderRecurse(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        if (root.left != null) {
            preorderRecurse(root.left, res);
        }
        if (root.right != null) {
            preorderRecurse(root.right, res);
        }
    }

    public static List<Integer> inorderTraverse(TreeNode root, boolean recursive) {
        List<Integer> res = new ArrayList<>();
        if (!recursive) {
            Stack<TreeNode> stack = new Stack<>();
            while (root != null || !stack.isEmpty()) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    TreeNode p = stack.pop();
                    res.add(p.val);
                    root = p.right;
                }
            }
        } else {
            inorderRecurse(root, res);
        }
        return res;
    }

    private static void inorderRecurse(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inorderRecurse(root.left, res);
        }
        res.add(root.val);
        if (root.right != null) {
            inorderRecurse(root.right, res);
        }
    }

    public static List<Integer> postorderTraverse(TreeNode root, boolean recursive) {
        List<Integer> res = new ArrayList<>();
        if (!recursive) {
            Stack<TreeNode> stack = new Stack<>();
            HashSet<TreeNode> passedParents = new HashSet<>();
            while (root != null || !stack.isEmpty()) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    if (passedParents.contains(root = stack.peek()) || root.right == null) {
                        res.add(root.val);
                        stack.pop();
                        root = null;
                    } else {
                        passedParents.add(root);
                        root = root.right;
                    }
                }
            }
        } else {
            postorderRecurse(root, res);
        }
        return res;
    }

    private static void postorderRecurse(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            postorderRecurse(root.left, res);
        }
        if (root.right != null) {
            postorderRecurse(root.right, res);
        }
        res.add(root.val);
    }

    public static List<Integer> levelOrderTraverse(TreeNode root, boolean zigzag) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        if (!zigzag) {
            while (!queue.isEmpty()) {
                res.add((root = queue.poll()).val);
                if (root.left != null) {
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                }
            }
        } else {
            boolean even = false;
            while (!queue.isEmpty()) {
                int lastLevelNum = queue.size();
                Deque<TreeNode> lastLevel = new LinkedList<>();
                for (int i = 0; i < lastLevelNum; i++) {
                    lastLevel.offer(root = queue.poll());
                    assert root != null;
                    if (root.left != null) {
                        queue.addLast(root.left);
                    }
                    if (root.right != null) {
                        queue.addLast(root.right);
                    }
                }
                while (!lastLevel.isEmpty()) {
                    res.add(even ? lastLevel.removeLast().val : lastLevel.removeFirst().val);
                }
                even = !even;
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
