package structure;

/**
 * @author Du
 * @description
 * @time 2022/1/6 12:55
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int x) {
        this.val = x;
    }

    public static ListNode getList(int... arr) {
        if (arr == null || arr.length == 0) {
            return new ListNode();
        }
        int length = arr.length;
        ListNode node = new ListNode(arr[0]);
        ListNode head = node;
        for (int i = 1; i < length; i++) {
            node.next = new ListNode(arr[i]);
            node = node.next;
        }
        return head;
    }

    public static void printNodes(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        ListNode node = this;
        while (node != null) {
            output.append(node.val).append(" ");
            node = node.next;
        }
        return output.toString();
    }
}
