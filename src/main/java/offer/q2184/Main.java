package offer.q2184;

import structure.ListNode;

/**
 * @author Du
 * @description
 * @time 2022/1/6 13:18
 */
public class Main {
    public static void main(String[] args) {
        for (int i : new Solution2().reversePrint(ListNode.getList(1, 3, 2))) {
            System.out.print(i + " ");
        }
    }
}
