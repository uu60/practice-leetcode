package leetcode.q92_反转链表2;

import structure.ListNode;

/**
 * @author octopus
 * @since 2023/4/28 17:35
 */
public class Solution2 implements Question {

    /**
     * 一次遍历实现
     */
    @Override
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 设置虚拟头节点
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode cur = dummy;
        ListNode prev = null;
        ListNode next = dummy.next;
        ListNode lastTail = null;
        // cur的位置
        int pos = 0;

        while (true) {
            if (pos >= left && pos <= right) {
                // 走到第一个位置，记录上一部分尾部
                if (lastTail == null) {
                    lastTail = prev;
                    prev = cur;
                } else {
                    // 把cur插入到lastTail之后
                    prev.next = next;
                    cur.next = lastTail.next;
                    lastTail.next = cur;
                }
                cur = next;
                pos++;
                if (pos > right) {
                    break;
                }
                next = next.next;
            } else {
                prev = cur;
                cur = next;
                pos++;
                next = next.next;
            }

        }

        return dummy.next;
    }
}
