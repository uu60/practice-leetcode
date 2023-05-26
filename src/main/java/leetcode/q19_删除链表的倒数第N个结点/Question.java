package leetcode.q19_删除链表的倒数第N个结点;

import structure.ListNode;


import java.util.ArrayList;
import java.util.List;

public interface Question {
    ListNode removeNthFromEnd(ListNode head, int n);
}

class Test {
    public static void main(String[] args) {
        // Validator.exec(Question.class, 2, new ListNode(1, 2, 3, 4, 5), 2);
    }
}

class Solution2 implements Question {

    int level = 0;
    boolean done = false;

    // 不使用额外存储
    // 递归
    @Override
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = recurse(head, n);
        return done ? node : head.next;
    }

    private ListNode recurse(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode nextNode = recurse(head.next, n);
        if (!done) {
            level++;
            // 当前是倒数第level个结点
            if (level == n + 1) {
                head.next = nextNode.next;
                done = true;
            }
        }
        return head;
    }
}

class Solution1 implements Question {

    // 额外存储
    @Override
    public ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> nodes = new ArrayList<>();
        while (head != null) {
            nodes.add(head);
            head = head.next;
        }
        int size = nodes.size();
        // 如果删除头结点
        if (size == n) {
            return size == 1 ? null : nodes.get(1);
        }
        // 删除结点的前一个
        nodes.get(size - n - 1).next = (n == 1 ? null : nodes.get(size - n + 1));
        return nodes.get(0);
    }
}