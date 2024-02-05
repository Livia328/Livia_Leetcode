public class Q2 {
    /**
     * 2 - 4 - 3
     * p1
     * 
     * 5 - 6 - 4
     * p2
     * 
     * 7   0   8
     * p
     * 
     * carry
     * 
     * 一定要注意move pointers
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        int carry = 0;
        while (p1 != null && p2 != null) {
            int val = p1.val + p2.val + carry;
            carry = val / 10;
            val = val % 10;
            p.next = new ListNode(val);
            p = p.next;
            p1 = p1.next;
            p2 = p2.next;
        }
        while (p1 != null) {
            int val = p1.val + carry;
            carry = val / 10;
            val = val % 10;
            p.next = new ListNode(val);
            p = p.next;
            p1 = p1.next;
        }
        while (p2 != null) {
            int val = p2.val + carry;
            carry = val / 10;
            val = val % 10;
            p.next = new ListNode(val);
            p = p.next;
            p2 = p2.next;
        }
        // 最后要check一下carry还有没有
        if (carry != 0) {
            p.next = new ListNode(1);
        }
        return dummy.next;
    }
}
