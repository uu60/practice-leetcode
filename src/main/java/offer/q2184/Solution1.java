package offer.q2184;

import structure.ListNode;

import java.util.ArrayList;

/**
 * @author Du
 * @description
 * @time 2022/1/6 12:54
 */
public class Solution1 implements Question {

    // My answer 1 using recursion
    @Override
    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        ArrayList<Integer> list = new ArrayList();
        recurse(head, list);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void recurse(ListNode head, ArrayList list) {
        if (head.next != null) {
            recurse(head.next, list);
        }
        list.add(head.val);
    }
}
