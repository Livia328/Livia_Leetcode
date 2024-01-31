public class Q21 {
    /**
     * list1 1->2->3
     * list2 1->3->4
     * 
     * res:
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode p1 = list1;
        ListNode p2 = list2;
        ListNode head = new ListNode(-1);
        ListNode p = head;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p.next = new ListNode(p1.val);
                p1 = p1.next;
            } else {
                p.next = new ListNode(p2.val);
                p2 = p2.next;
            }
            p = p.next;
        }
        while (p1 != null) {
            p.next = new ListNode(p1.val);
            p1 = p1.next;
            p = p.next;
        }
        while (p2 != null) {
            p.next = new ListNode(p2.val);
            p2 = p2.next;
            p = p.next;
        }
        return head.next;
    }
}
