import java.text.BreakIterator;
import java.util.*;

import org.w3c.dom.ls.LSException;

public class tmp {
    /**
     * 1 -> 2 -> 3 -> 4
     * s
     * f
     */
    public void reorderList(ListNode head) {
        // divide into 2 parts
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode head2 = slow.next;
        slow.next = null;
        // reverse 
        head2 = reverse(null, head2);
        // reorder
        // 1 -> 2
        // p1
        // 3 -> 4
        // p2
        ListNode p1 = head; ListNode p2 = head2;
        while (p2 != null) {
            ListNode tmp = p1.next;
            p1.next = p2;
            p2 = p2.next;
            p1.next.next = tmp;
            // move pointers
            p1 = tmp;
        }

    }

    public ListNode reverse(ListNode pre, ListNode cur) {
        if (cur == null) {
            return pre;
        }
        ListNode after = cur.next;
        cur.next = pre;
        return reverse(cur, after);
    }
}
