package structure.complexnode;

import structure.ListNode;

import java.util.ArrayList;

/**
 * @author Du
 * @description 复杂链表，random指向随机node或null
 * @time 2022/1/6 13:51
 */
public class Node extends ListNode {
    public Node next;
    public Node random;

    public Node(int val) {
        super(val);
        this.next = null;
        this.random = null;
    }

    public static Node linkNodes(int[] vals, int[] rands) {
        if (vals.length != rands.length) {
            return null;
        }
        ArrayList<Node> list = new ArrayList<>();
        for (int i : vals) {
            list.add(new Node(i));
        }
        for (int i = 0; i < vals.length; i++) {
            list.get(i).next = i == vals.length - 1 ? null : list.get(i + 1);
            list.get(i).random = rands[i] < 0 ? null : list.get(rands[i]);
        }
        return list.get(0);
    }
}
