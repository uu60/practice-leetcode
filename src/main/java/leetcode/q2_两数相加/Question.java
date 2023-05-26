package leetcode.q2_两数相加;

import structure.ListNode;


public interface Question {
    ListNode addTwoNumbers(ListNode l1, ListNode l2);
}

class Test {
    public static void main(String[] args) {

    }
}

/**
 * 题解目录
 * {@link Solution1}不使用新链表
 */
class Solution1 implements Question {
    /*
        l1
        '2' -> '4' -> '3'
        '5' -> '6' -> '4'
        l2
     */

    /**
     * j击败100-74
     * 不使用新链表
     * @param l1
     * @param l2
     * @return
     */
    @Override
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode head = l2;
        ListNode tail = null;
        int extra = 0;
        while (l2 != null) {
            int val = (l1 == null ? 0 : l1.val) + l2.val + extra;
            l2.val = val % 10;
            extra = val / 10;
            tail = l2;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2.next == null) {
                if (l1 == null) {
                    break;
                }
                l2 = l1;
                l1 = null;
            } else {
                l2 = l2.next;
            }
            tail.next = l2;
        }
        if (extra == 1) {
            tail.next = new ListNode(1);
        }
        return head;
    }
}
