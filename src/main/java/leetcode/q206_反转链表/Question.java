package leetcode.q206_反转链表;

import structure.ListNode;


import java.util.List;

public interface Question {
    ListNode reverseList(ListNode head);
}

class Test {
    public static void main(String[] args) {
        ListNode.printNodes(new Solution3().reverseList(ListNode.getList(1, 2, 3, 4, 5)));
    }
}

class Solution4 implements Question {

    @Override
    public ListNode reverseList(ListNode head) {
        return null;
    }
}

class Solution3 implements Question {

    @Override
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        // 迭代
        ListNode prev = null, cur = head, next = cur.next;
        while (cur != null) {
            cur.next = prev;
            prev = cur;
            cur = next;
            next = next == null ? null : next.next;
        }
        return prev;
    }
}

/**
 *      题解目录      时间击败 空间击败 时间复杂度 空间复杂度 描述
 * {@link Solution1}   100     17     O(N)      O(N)    递归解决
 * {@link Solution1}   100     27     O(N)      O(1)    迭代解决
 *
 */

class Solution2 implements Question {

    @Override
    public ListNode reverseList(ListNode head) {
        ListNode n1 = null, n2 = head;
        while (n2 != null) {
            ListNode temp = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = temp;
        }
        return n1;
    }
}

class Solution1 implements Question {

    // 递归方式
    @Override
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}