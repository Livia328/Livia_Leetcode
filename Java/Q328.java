public class Q328 {
    /*
     * 用两个头odd和even分别记录, 这两个都是dummy
     * 
     * index记录当前node的index
     * 碰到index是奇数就在odd后面新建node
     * index是偶数就append在even后面
     */
    public ListNode oddEvenList1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode oddHead = new ListNode(-1);
        ListNode evenHead = new ListNode(-1);
        ListNode odd = oddHead, even = evenHead;
        ListNode p = head;
        int index = 1;
        while (p != null) {
            if (index % 2 == 0) {
                even.next = new ListNode(p.val);
                even = even.next;
            } else {
                odd.next = new ListNode(p.val);
                odd = odd.next;
            }
            p = p.next;
            index++;
        }
        // 把even连在odd后面
        odd.next = evenHead.next;
        // 把evenhead拿下来
        evenHead.next = null;
        return oddHead.next;
    }

    /*
     * 交叉更改的版本
     * 
     * 初始
     * 1    ->    2 -> 3 -> 4 -> 5
     * oddHead 
     * odd    
     *         evenHead
     *           even
     * 
     * 下一步要将1和3连
     * 所以odd.next = even.next
     * 然后将odd移动到下一个
     * 1    ->    2 -> 3 -> 4 -> 5
     * oddHead 
     * odd    
     *         evenHead
     *           even
     * 此时odd指向3
     * 再连接even，2 -> 4
     * even.next = odd.next
     * 移动even指针
     * 1    ->    2 -> 3 -> 4 -> 5
     * oddHead 
     *                odd    
     *         evenHead
     *           even
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
