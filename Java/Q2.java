public class Q2 {
    /**
     * 模拟
     * 
     *          2 - 4 - 3
     *          p1
     * 
     *          5 - 6 - 4
     *          p2
     * 
     * dummy -> 7   0   8
     * p
     * 
     * carry
     * 
     * (p1 + p2 + carry) % 10
     * 用新node链接在dummy后面
     * 
     * 注意最后要判断carry是否为1
     * 是的话，要多加一个
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
