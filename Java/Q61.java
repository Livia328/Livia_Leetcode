public class Q61 {
    /*
     * 提问，k一定比linkedlist的size小吗
     * 
     * 要把linkedlist rotate两次
     * 比如一开始是1-2-3-4-5 k = 2
     *               p
     * 那么就是 4- 5- 1-2-3
     * 
     * 所以我们要找到第k - 1个位置
     * 也就是上面例子中的3
     * 
     * 然后把linkedlist分为两段
     * 然后把前后分离开，把前面的连接到后面上
     * 
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
