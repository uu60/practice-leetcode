package offer.q2202;

import structure.ListNode;

/**
 * @author Du
 * @description 使用迭代
 * @time 2022/1/6 13:41
 */
public class Solution2 implements Question {

    @Override
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode left = null, right = head;
        while (right != null) {
            ListNode temp = right.next;
            right.next = left;
            left = right;
            right = temp;
        }
        return left;
    }
}
