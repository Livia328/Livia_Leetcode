public class Q92 {
    /*
     * 递归
     * 
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 如果m == 1，等于反转前right个元素
        if (left == 1) {
            return reverseN(head, right);
        }
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    /*
     * 反转以Head为起点的前n个
     */
    // 记录下一个
    ListNode successor = null;
    public ListNode reverseN(ListNode head, int n) {
        // base case,反转一个元素，也就是它本身
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head; // 调转箭头方向
        head.next = successor;
        return last;
    }

    /*
     * 头插法：定义guard and point
     * 将g定位到要left之前，p定位到left
     * 将p之后的元素删除，加到g之后
     * 
     * 直到p到达right
     * 
     * https://leetcode.cn/problems/reverse-linked-list-ii/solutions/138910/java-shuang-zhi-zhen-tou-cha-fa-by-mu-yi-cheng-zho/
     * 
     * dummy  - 1 - 2 - 3 - 4 - 5 left=2, right=4
     *          g   p
     * dummy  - 1 - 3 - 2 - 4 - 5 删除3，放到g后面
     *          g       p
     * dummy  - 1 - 3 - 4 - 2 - 5 删除4，放到g后面
     *          g           p
     */
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode g = dummy;
        ListNode p = dummy.next;
        // 把g移到left之前，p移到left上
        for (int i = 0; i < left - 1; i++) {
            g = g.next;
            p = p.next;
        }
        // 开始头插入法
        for (int i = 0; i < right - left; i++) {
            ListNode removed = p.next;
            p.next = p.next.next;

            removed.next = g.next;
            g.next = removed;
        }
        return dummy.next;
    }
}
