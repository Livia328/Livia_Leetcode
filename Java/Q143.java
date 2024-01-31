public class Q143 {
    /**
     * divide the linked list into 2 part
     * reverse the second part
     * connect
     * 
     * 1 -> 2 -> 3 -> 4
     *      s
     *           f
     */
    public void reorderList(ListNode head) {
       // divide into 2 part
       ListNode slow = head;
       ListNode fast = head;
       while (fast.next != null && fast.next.next != null ) {
           slow = slow.next;
           fast = fast.next.next;
       }
       ListNode head2 = slow.next;
       slow.next = null;
       // list1: 1  2
       //        p1 tmp
       // list2:   4->3
       ListNode list2 = reverseLinkedList(null, head2);
       ListNode p1 = head; ListNode p2 = list2;
       while (p2 != null) {
           ListNode tmp = p1.next;
           p1.next = p2;
           p2 = p2.next;
           p1.next.next = tmp;
           p1 = tmp;
       }

   }

   /**
    * null -> 1 -> 2 -> 3 -> 4
    * pre
    *        cur 
    *             after
    */
   public ListNode reverseLinkedList(ListNode pre, ListNode cur) {
       if (cur == null) return pre;
       ListNode tmp = cur.next;
       cur.next = pre;
       return reverseLinkedList(cur, tmp);
   }
}
