package leetcode.q24_两两交换链表中的节点;

import structure.ListNode;


public interface Question {
    ListNode swapPairs(ListNode head);
}

class Test {
    public static void main(String[] args) {
        ListNode listNode = ListNode.getList(1, 2, 3, 4);
    }
}

class Solution2 implements Question {

    /**
     * 递归
     * @param head
     * @return
     */
    @Override
    public ListNode swapPairs(ListNode head) {
        return recurse(head, false);
    }

    private ListNode recurse(ListNode node, boolean even) {
        if (node == null) {
            return null;
        }

        // 如果当前深度的结点是奇数点
        if (!even) {
            // 当前结点的下一个，已完成后面的拼接
            ListNode next = recurse(node.next, true);
            if (next == null) {
                return node;
            }
            node.next = next.next;
            next.next = node;
            return next;
        } else {
            // 如果是偶数点
            node.next = recurse(node.next, false);
            return node;
        }
    }
}

class Solution1 implements Question {

    /**
     * 迭代
     * @param head
     * @return
     */
    @Override
    public ListNode swapPairs(ListNode head) {
        ListNode node1 = head;
        ListNode lastNode2 = null;
        while (node1 != null) {
            ListNode node2 = node1.next;
            if (node2 != null) {
                ListNode nextNode1 = node2.next;
                node2.next = node1;
                node1.next = nextNode1;
                if (lastNode2 != null) {
                    lastNode2.next = node2;
                } else { // 上一次的尾巴为null 说明是第一次
                    head = node2;
                }
                lastNode2 = node1;
                node1 = nextNode1;
            } else {
                break;
            }
        }
        return head;
    }
}


class Solution0 implements Question {

    // 终止条件：链表长度<=1
    // 思想：返回链表的头结点，递归的上一层继续操作
    @Override
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }
}