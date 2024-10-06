import java.util.HashSet;
import java.util.Set;

public class Q160 {
    /*
     * hashmap
     * 因为有交点，所以先遍历一遍A，把所有node放到hashset中
     * 再遍历一遍B，第一个重复的就是交点
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode p1 = headA;
        while(p1 != null) {
            set.add(p1);
            p1 = p1.next;
        }
        p1 = headB;
        while (p1 != null) {
            if (set.contains(p1)) {
                return p1;
            }
            p1 = p1.next;
        }
        return null;
    }

    /*
     * 让两个指针走同样的步数
     * A结束了就走B，B结束了就走A
     * 这样最后A或者B都会停留在交叉点
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA, B = headB;
        while (A != B) {
            A = A == null ? headB : A.next;
            B = B == null ? headA : B.next;
        }
        return A;
    }
}
