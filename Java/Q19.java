public class Q19 {
    /**
     * 注意！这题一定要记得用dummy node
     * 因为有可能要删除的就是head
     * 
     * 
     * n = 2
     * null -> 1 -> 2 -> 3 -> 4 -> 5
     *  pre
     *         s
     *         f
     * 
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode s = dummy;
        ListNode f = dummy;
        ListNode pre = null;
        // 先让f走n格
        for (int i = 0; i < n ; i++) {
            f = f.next;
        }
        while (f != null) {
            pre = s;
            s = s.next;
            f = f.next;
        }
        pre.next = s.next;
        s.next = null;
        return dummy.next;
    }
}
