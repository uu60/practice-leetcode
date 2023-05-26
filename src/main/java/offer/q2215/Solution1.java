package offer.q2215;

import structure.complexnode.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Du
 * @description 暴力
 * @time 2022/1/6 13:55
 */
public class Solution1 implements Question {
    @Override
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        List<Node> newNodeList = new ArrayList<>();
        HashMap<Integer, Node> oldRandomIndexMap = new HashMap<>();
        HashMap<Node, Integer> oldNodeIndexMap = new HashMap<>();

        int index = 0;
        while (head != null) {
            newNodeList.add(new Node(head.val));
            oldRandomIndexMap.put(index, head.random);
            oldNodeIndexMap.put(head, index);
            head = head.next;
            index++;
        }
        for (int i = 0; i < newNodeList.size(); i++) {
            if (i != newNodeList.size() - 1) {
                newNodeList.get(i).next = newNodeList.get(i + 1);
            }
            newNodeList.get(i).random = oldRandomIndexMap.get(i) == null ? null : newNodeList.get(oldNodeIndexMap.get(oldRandomIndexMap.get(i)));
        }
        return newNodeList.get(0);
    }
}
