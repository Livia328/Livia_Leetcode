import java.util.*;

import Oracel.queuePQ;

public class tmp {
    /*
     * 注意题干，重复的都要删除，而不是留一个
     * 可以有一个dummy node
     * 
     * 两个pointer，一个表示所有已经检查过，无duplicate的
     * 一个表示目前正在便利的
     * 
     * dummy - 2 - 2 - 2 - 3
     *  p     cur
     * 
     * 如果不重复，直接把cur接到p的后面
     * 
     * 如果cur和cur.next.val一样
     * 那么说明有重复
     * 一直找到无重复位置，将无重复的接到p后面
     * 
     * 比如这里
     * cur.val = cur.val.next
     * 就把cur往后
     * 
     * dummy - 2 - 2 - 2 - 3 - 3
     *  p                 cur
     * 
     * 到这里停住
     * 我们要接到P后面的是3
     * 
     * 但是，当发现了之后不能马上连
     * 一定要等到下一轮while loop再连！！！
     * 否则下一个数字是重复的不能识别出来
     * 
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p = dummy, cur = head;
        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                cur = cur.next;
                if (cur == null) {
                    p.next = null;
                }
            } else {
                p.next = cur;
                p = p.next;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
    
    public static void main(String[] args) {
        removeDuplicates("deeedbbcccbdaa",3);
    }
}
