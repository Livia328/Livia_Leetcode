public class Q86 {
    /*
     * 先把它分为两个链表
     * 一个是所有比x小的node
     * 一个是所有大于等于x的node
     * 再合在一起
     * 
     * 因为是pointer从前到后
     * 所以也满足保留原来的顺序
     * 
     * dummy1
     * dummy2
     * 
     */
    public ListNode partition(ListNode head, int x) {
        // 存放所有比x小的
        ListNode dummy1 = new ListNode();
        ListNode dummy2 = new ListNode();
        ListNode p1 = dummy1, p2 = dummy2;
        ListNode p = head;
        while (p != null) {
            if (p.val >= x) {
                p2.next = p;
                p2 = p2.next;
            } else {
                p1.next = p;
                p1 = p1.next;
            }
            // 因为我们是直接更改指针而没有new一个新的
            // 所以不能直接p = p.next
            // 而是要先断开一下p的指针
            ListNode tmp = p.next;
            p.next = null;
            p = tmp;
        }
        // 连接p1和p2
        p1.next = dummy2.next;
        return dummy1.next;

    }
}
