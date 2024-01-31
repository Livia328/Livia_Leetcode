public class Q206 {
    /**
     * iterative
     *  null  <- 1 <- 2 <- 3 <- 4 <- 5
     *                             prev
                                       cur
     *                                after
     * 每次将after的指针移向prev
     * after是为了防止
     * 
     */
    public ListNode reverseList(ListNode head) {
        // corner case是由于我下面要写的ListNode after = head.next;决定的
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode cur = head;
        ListNode after = head.next;
        while (after != null) {
            // 先存一下after，不然一会儿找不着了
            after = cur.next;
            cur.next = prev;
            // move
            prev = cur;
            cur = after;
        }
        return prev;
    }

    /**
     * do it recursively
     * 
     * 1 -> 2 -> 3 -> 4 -> 5
     *                    prev cur
     * 
     * helper(ListNode pre, ListNode cur)
     * 
     * task is to modify the pointer of curNode to the previous one
     * 
     * recursive rule, pass cur and the next node of cur
     * 
     * base case
     * cur is null, prev is the new head
     * 
     */
    public ListNode reverseList2(ListNode head) {
        return helper(null, head);
    }

    public ListNode helper(ListNode prev, ListNode cur) {
        // base case
        if (cur == null) {
            return prev;
        }
        // action: change the pointer
        ListNode after = cur.next;
        cur.next = prev;
        // recursively
        return helper(cur, after);
    }
}
