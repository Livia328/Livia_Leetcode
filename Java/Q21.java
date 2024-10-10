public class Q21 {
    /**
     * remind me of merge sort
     * 
     * 两个指针分别指向list1和list2的头
     * 每次比较p1和p2的值
     * 
     * 因为我们不知道一开始的node是哪个，可以用dummy head
     * 
     * list1 1->2->3
     *       p1
     * list2 1->3->4
     *       p2
     * 
     * dummy -> 
     * p
     * 
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
