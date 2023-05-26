package leetcode.q25_K个一组翻转链表;

import structure.ListNode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public interface Question {
    ListNode reverseKGroup(ListNode head, int k);
}

class Test {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {

        ListNode listNode = ListNode.getList(1, 2, 3, 4, 5);
        ListNode.printNodes(new Solution3().reverseKGroup(listNode, 2));
    }
}

class Solution3 implements Question {

    @Override
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }
        ListNode prevTail = null, nextHead = null;
        ListNode curHead = head, curTail = null;
        ListNode newHead = null;
        while (true) {
            for (int i = 0; i < k; i++) {
                curTail = curTail == null ? curHead : curTail.next;
                if (curTail == null) {
                    return newHead;
                }
            }
            nextHead = curTail.next;
            reverseList(curHead, curTail);
            if (prevTail != null) {
                prevTail.next = curTail;
            }
            if (newHead == null) {
                newHead = curTail;
            }
            prevTail = curHead;
            curHead.next = nextHead;
            curTail = curHead;
            curHead = nextHead;
        }
    }

    ListNode reverseList(ListNode head, ListNode tail) {
        if (head == tail) {
            return head;
        }
        ListNode newHead = reverseList(head.next, tail);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}

class Solution2 implements Question {

    @Override
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curHead = head;
        ListNode curTail = null;
        ListNode nextHead = null;
        ListNode newHead = null;
        ListNode lastTail = null;
        boolean first = true;
        while (true) {
            for (int i = 0; i < k; i++) {
                curTail = curTail == null ? curHead : curTail.next;
                if (curTail == null) {
                    return newHead;
                }
            }
            if (first) {
                newHead = curTail;
                first = false;
            }
            nextHead = curTail.next;
            reverse(curHead, curTail);
            if (lastTail != null) {
                lastTail.next = curTail;
            }
            lastTail = curHead;
            curHead.next = nextHead;
            curHead = nextHead;
            curTail = null;
            if (nextHead == null) {
                break;
            }
        }
        return newHead;
    }

    private ListNode reverse(ListNode head, ListNode tail) {
        if (head == tail) {
            return tail;
        }
        ListNode newHead = reverse(head.next, tail);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}

class Solution1 implements Question {

    @Override
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        ListNode tail = head;
        ListNode preTail = hair;
        while (true) {
            for (int i = 0; i < k - 1 && tail != null; i++) {
                tail = tail.next;
            }
            if (tail == null) {
                return hair.next;
            }
            ListNode nextHead = tail.next;
            tail.next = null;
            preTail.next = reverseAll(head);
            preTail = head;
            head.next = nextHead;
            head = tail = nextHead;
        }
    }

    private ListNode reverseAll(ListNode head) {
        ListNode p1 = null, p2 = head;
        while (p2 != null) {
            ListNode temp = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = temp;
        }
        return p1;
    }
}