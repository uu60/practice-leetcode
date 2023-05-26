package leetcode.q92_反转链表2;


import structure.ListNode;

/**
 * @author octopus
 * @since 2023/4/28 17:12
 */
public class Solution1 implements Question {

    @Override
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 如果要反转的长度为1，直接返回
        if (left == right) {
            return head;
        }
        ListNode part1Tail = null, part2Head = null, part2Tail = null, part3Head = null;
        ListNode ptr = head;
        // ptr位置
        int pos = 1;
        while (ptr != null) {
            if (left == 1) {
                part2Head = head;
            }
            // 如果下一个是要反转的开始
            else if (pos + 1 == left) {
                part1Tail = ptr;
                part2Head = ptr.next;
            }
            // 如果下一个是要反转的最后
            if (pos == right) {
                // 将是最后反转后的part2头
                part2Tail = ptr;
                part3Head = ptr.next;
                break;
            }
            ptr = ptr.next;
            pos++;
        }
        reverse(part2Head, part3Head);
        if (part1Tail != null) {
            part1Tail.next = part2Tail;
        } else {
            head = part2Tail;
        }
        part2Head.next = part3Head;

        return head;
    }

    private ListNode reverse(ListNode head, ListNode nextPart) {
        if (head.next == nextPart) {
            return head;
        }
        ListNode newHead = reverse(head.next, nextPart);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
