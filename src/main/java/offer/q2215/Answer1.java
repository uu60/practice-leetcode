package offer.q2215;

import structure.complexnode.Node;

import java.util.HashMap;

/**
 * @author Du
 * @description
 * @time 2022/1/6 14:53
 */
public class Answer1 implements Question {
    private HashMap<Node, Node> map = new HashMap<>();

    @Override
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        if (!map.containsKey(head)) {
            Node node = new Node(head.val);
            map.put(head, node);
            node.next = copyRandomList(head.next);
            node.random = copyRandomList(head.random);
        }
        return map.get(head);
    }
}
