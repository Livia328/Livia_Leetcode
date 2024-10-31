public class Q2807 {
    /*
     * 18 -> 6 -> 10 -> 3
     * pre
     *     cur
     * 
     * pre -> 6 -> cur
     * 
     * 怎么得到GreatestCommonDivisors？
     * 辗转相除
     * 
     */
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode pre = head, cur = head.next;
        while (cur != null) {
            ListNode newNode = new ListNode(getGCD(pre.val, cur.val));
            pre.next = newNode;
            newNode.next = cur;
            pre = cur;
            cur = cur.next;
        }
        return head;
    }

    public int getGCD(int a, int b) {
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }
}
