package offer.q2184;

import structure.ListNode;

import java.util.Stack;

/**
 * @author Du
 * @description
 * @time 2022/1/6 13:05
 */
public class Solution2 implements Question {
    @Override
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int[] res = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()) {
            res[i++] = stack.pop();
        }
        return res;
    }
}
