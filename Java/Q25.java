public class Q25 {
    /**
     * 首先提问：
     * 如果不足k个是保留原样嘛？
     * 
     * 写一个helper function来reverse某一段[a, b)
     * recusively调用reverseKGroup，分别reverse每一组
     * 
     * 1 - 2 - 3 - 4 - 5， k = 2
     * a       b
     * 
     * reverse后会有一个新head，也就是2 - 1
     * 
     * 然后再以b为起点，reverseKGroup(b, 2)
     * 然后把这两段接上
     * 
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        ListNode a,b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            // 不足k个，不用反转了
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
