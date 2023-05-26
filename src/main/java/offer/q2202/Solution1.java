package offer.q2202;

import structure.ListNode;

/**
 * @author Du
 * @description 使用递归
 * @time 2022/1/6 13:33
 */
public class Solution1 implements Question {
    @Override
    public ListNode reverseList(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
