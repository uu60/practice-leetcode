package leetcode.q92_反转链表2;

import structure.ListNode;

/**
 * @author octopus
 * @since 2023/4/28 17:11
 */
public interface Question {
    ListNode reverseBetween(ListNode head, int left, int right);

    public static void main(String[] args) {
        ListNode.printNodes(new Solution2().reverseBetween(ListNode.getList(1, 2, 3, 4, 5, 6), 1, 6));
    }
}
