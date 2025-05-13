import java.util.Currency;

public class Q148 {
    /*
     * merge sort
     * 每次找到linked list的中点，分成两半
     * 直到只剩下一个node
     * 
     * 然后再合并起来
     * 
     * 所以需要一个function去找到linkedlist的中间节点(876)
     * 还需要一个function去合并两个有序数组（21)
     */
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        // 找到中间节点，断开head2与前一个节点的链接
        ListNode head2 = getMiddleNode(head);
        // 不断递归调用
        head = sortList(head);
        head2 = sortList(head2);
        return mergetTwoSortedList(head, head2);
    }

    private ListNode getMiddleNode(ListNode head) {
        ListNode pre = head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null  && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 断开slow的前一个节点和slow的链接
        pre.next = null;
        return slow;
    }

    private ListNode mergetTwoSortedList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode p = dummy; // p用来traverse整个linkedlist
        while (l1 != null && l2 != null) {
            // 把比较小的那一个放进去
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        // 把剩下的链表接上去
        p.next = l1 != null ? l1 : l2;
        return dummy.next;
    }
}