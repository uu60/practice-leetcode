package offer.q2215;

import structure.complexnode.Node;

/**
 * @author Du
 * @description
 * @time 2022/1/6 14:32
 */
public class Main {
    public static void main(String[] args) {
        Node node = new Answer1().copyRandomList(Node.linkNodes(
                new int[]{7, 13, 11, 10, 1},
                new int[]{-1, 0, 4, 2, 0}
        ));
        while (node != null) {
            System.out.println(node.val + " " + (node.random == null ? "null" : node.random.val));
            node = node.next;
        }
    }
}
