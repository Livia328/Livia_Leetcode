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
    public void reorderList1(ListNode head) {
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

   /**
    * 因为要倒叙，所以想到stack
    * 1 - 2 - 3 - 4
    * p
    * 先全都放到stack里
    * stack：1，2， 3， 4
    *
    * 每次pop出来last node
    * 也就是4
    * 把它放到p和p.next之中
    *
    * p.next = lastNode;
    * lastNode.next = next;
    * p = next;
    *
    * 直到last node == next，或者lastnode.next == next
    *      奇数的情况                偶数的情况
    * 要把lastnode.next = null
    * 否则会环形
    */
    public void reorderList(ListNode head) {
        Stack<ListNode> stk = new Stack<>();
        // 先把所有节点装进栈里，得到倒序结果
        ListNode p = head;
        while (p != null) {
            stk.push(p);
            p = p.next;
        }

        p = head;
        while (p != null) {
            // 链表尾部的节点
            ListNode lastNode = stk.pop();
            ListNode next = p.next;
            if (lastNode == next || lastNode.next == next) {
                // 结束条件，链表节点数为奇数或偶数时均适用
                lastNode.next = null;
                break;
            }
            p.next = lastNode;
            lastNode.next = next;
            p = next;
        }
    }
}
