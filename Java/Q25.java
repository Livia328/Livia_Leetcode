public class Q25 {
    /**
     * 
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        ListNode a,b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) {
                return head;
            }
            b = b.next;
        }
        ListNode newHead = reverse(a, b);
        a.next = reverseKGroup(b, k);
        return newHead;

    }

    public ListNode reverse(ListNode a, ListNode b) {
        ListNode pre, cur, nex;
        pre = null;
        cur = a;
        nex = a;
        while ( cur != b) {
            nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }
        return pre;
    }
}
