import java.util.PriorityQueue;

public class Q23 {
    /*
     * [1,4,5],
     *    p1
     * 
     * [1,3,4],
     * p2
     * 
     * [2,6]
     * p3
     * 
     * PriorityQueue<ListNode>  1, 2, 4
     * 
     * dummy -> 1 -> 1 -> 2 -> 3 -> 4 -> 4
     * p
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        // add node into queue
        for (ListNode cur : lists) {
            if (cur != null) {
                pq.add(cur);
            }
        }
        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            p.next = new ListNode(cur.val);
            if (cur.next != null) {
                pq.add(cur.next);
            }
            p = p.next;
        }
        return dummy.next;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        ListNode dummy = new ListNode();
        ListNode p = dummy;
        while (!isEmpty(lists)) {
            int min = Integer.MAX_VALUE;
            int index = 0;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null && lists[i].val < min) {
                    min = lists[i].val;
                    index = i;
                }
            }
            // add the min value to p
            p.next = new ListNode(min);
            p = p.next;
            lists[index] = lists[index].next;
        }
        return dummy.next;
    }

    public boolean isEmpty(ListNode[] lists) {
        for (ListNode cur : lists) {
            if (cur != null) {
                return false;
            }
        }
        return true;
    }
}
