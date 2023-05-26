package leetcode.q42_接雨水;



import java.util.Stack;

public interface Question {
    int trap(int[] height);

    public static void main(String[] args) {
        System.out.println(new Solution2().trap(new int[]{4, 2, 0, 3, 2, 5}));
    }
}

