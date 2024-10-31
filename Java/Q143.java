public class Q143 {
    /**
     * 把linkedlist从中间分成两部分
     * 用快慢指针
     * 
     * 1 -> 2 -> 3 -> 4
     *      s
     *           f
     * 
     * 第一段 1 -> 2
     * 第二段 3 -> 4
     * 
     * 然后把第二段reverse一下
     * 4 -> 3
     * 
     * 然后把第一段和第二段交叉连接在一起
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
       //          p2
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
