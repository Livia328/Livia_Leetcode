public class Q61 {
    /*
     * 从倒数第k个数字开始切割
     * 即要找到pointer = size - k - 1
     */
    public ListNode rotateRight(ListNode head, int k) {
        // find the size of linkedlist
        int size = 0;
        ListNode p = head;
        while (p != null) {
            p = p.next;
            size++;
        }
        if (size != 0) {
            k = k % size;
        }
        k = size - k - 1; // pointer p should point at
        p = head;
        for (int i = 0; i < k; i++) {
            p = p.next;
        }
        // 将链表分成两段
        // p为第一段结尾，p.next为第二段开头
        if (p != null && p.next != null) {
            ListNode newHead = p.next;
            p.next = null;
            ListNode tmp = newHead;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = head;
            return newHead;
        }
        return head;
    }
}
