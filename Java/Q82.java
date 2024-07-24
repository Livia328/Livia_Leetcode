public class Q82 {
    /**
     * 注意题干，重复的所有都要删去，而不是留一个
     * 有一个dummy，如果是不一样的就接上去
     */
    public ListNode deleteDuplicates1(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode p = dummy, q = head;
        while (q != null) {
            if (q.next != null && q.val == q.next.val) {
                while (q.next != null && q.val == q.next.val) {
                    q = q.next;
                }
                q = q.next;
                if (q == null) {
                    p.next = null;
                }
            } else {
                // 不重复，接到q的后面
                p.next = q;
                p = p.next;
                q = q.next;
            }
        }
        return dummy.next;
    }

    /*
     * recursion
     * 
     * recursion function的含义是，返回以head为head的没重复linkedlist
     */
    public ListNode deleteDuplicates(ListNode head) {
        // base case
        if (head == null || head.next == null) {
            return head;
        }
        if (head.val != head.next.val) {
            head.next = deleteDuplicates(head.next);
            return head;
        }
        // have duplicates
        while (head.next != null && head.next.val == head.val) {
            head = head.next;
        }
        // 就不和前面的连起来了
        return deleteDuplicates(head.next);
    }
}