public class Q23 {
    public ListNode mergeKLists(ListNode[] lists) {
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
