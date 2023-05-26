package leetcode.q23_合并K个升序链表;

import structure.ListNode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Question {
    ListNode mergeKLists(ListNode[] lists);
}

class Test {
    public static void main(String[] args) {
        ListNode[] case1 = {
                ListNode.getList(1, 3, 4, 6, 8, 9, 12),
                ListNode.getList(1, 2, 5, 7, 11, 21, 24),
                ListNode.getList(-4, 0, 4, 7, 10, 14, 22, 29)
        };
    }
}

class Solution2 implements Question {

    /**
     * 改进Solution1 排序nodes数组
     * @param lists
     * @return
     */
    @Override
    public ListNode mergeKLists(ListNode[] lists) {
        // 保存所有链表的头结点
        List<ListNode> nodes = new ArrayList<>();
        // 按顺序插入
        for (ListNode head : lists) {
            binaryInsert(nodes, head);
        }
        // 合并结果
        ListNode res = null;
        ListNode tail = res;

        // 每次循环拼接一个
        while (!nodes.isEmpty()) {
            ListNode leastNode = nodes.get(0);
            if (leastNode == null) {
                break;
            }
            if (tail == null) {
                tail = leastNode;
                res = tail;
            } else {
                tail.next = leastNode;
                tail = tail.next;
            }
            // 二分插入上一次最小结点的next
            nodes.remove(0);
            ListNode next = leastNode.next;
            if (next != null) {
                binaryInsert(nodes, next);
            }
        }

        return res;
    }

    private void binaryInsert(List<ListNode> nodes, ListNode node) {
        if (node == null) {
            return;
        }
        int left = 0;
        int right = nodes.size() - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nodes.get(mid).val < node.val) {
                left = mid + 1;
            } else if (nodes.get(mid).val > node.val) {
                right = mid - 1;
            } else {
                nodes.add(mid, node);
                break;
            }
        }
        // 没有相等的结点
        if (left >= right) {
            nodes.add(right < 0 ? 0 : (nodes.get(left).val > node.val ? left : left + 1), node);
        }
    }

}

class Solution1 implements Question {

    /**
     * 正确但是耗时非常长
     * @param lists
     * @return
     */
    @Override
    public ListNode mergeKLists(ListNode[] lists) {
        // 保存所有链表的头结点
        List<ListNode> nodes = new ArrayList<>(Arrays.asList(lists));
        // 合并结果
        ListNode res = null;
        ListNode tail = res;

        // 每次循环拼接一个
        do {
            boolean done = true;
            // 判断是否还有剩余结点，并找到最小值
            int leastIdx = 0;
            for (int i = 0; i < nodes.size(); i++) {
                if (nodes.get(i) == null) {
                    continue;
                }
                // 当前结点比遍历过的最小点还小
                if (nodes.get(i).val <
                        (nodes.get(leastIdx) == null ?
                                Integer.MAX_VALUE : nodes.get(leastIdx).val)) {
                    leastIdx = i;
                }
                done = false;
            }
            // 上述遍历后发现已经结束
            if (done) {
                break;
            }
            ListNode leastNode = nodes.get(leastIdx);
            if (tail == null) {
                tail = leastNode;
                res = tail;
            } else {
                tail.next = leastNode;
                tail = tail.next;
            }
            nodes.set(leastIdx, leastNode.next);
        } while (true);

        return res;
    }
}
