public class Q147 {
    public ListNode insertionSortList(ListNode head) {
        // corner cases
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = head; // for comparison
        ListNode cur = head; // traverse cur node
        while (cur != null) {
            if (pre.val <= cur.val) {
                // no need for change
                // iterate forward
                pre = cur;
                cur = cur.next;
            } else {
                // find the right position from start
                ListNode p = dummy;
                while (p.next != cur && p.next.val <= cur.val) {
                    p = p.next;
                }
                // insert cur between p and p.next
                // dummy -> .. -> p -> pre -> cur -> cur.next
                pre.next = cur.next;
                cur.next = p.next;
                p.next = cur;
                cur = pre.next;
            }
        }
        return dummy.next;
    }
}
