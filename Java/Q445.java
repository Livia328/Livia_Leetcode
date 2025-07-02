import java.util.Stack;

public class Q445 {
    /*
     * 和Q2的不同就是这里的head是最低位
     * 所以可以先翻转链表，再和第2题一样
     * 
     * 
     * 模拟
     * 
     *          2 - 4 - 3
     *          p1
     * 
     *          5 - 6 - 4
     *          p2
     * 
     * dummy -> 7   0   8
     * p
     * 
     * carry
     * 
     * (p1 + p2 + carry) % 10
     * 用新node链接在dummy后面
     * 
     * 注意最后要判断carry是否为1
     * 是的话，要多加一个
     * 
     * 一定要注意move pointers
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode dummy = new ListNode();
        ListNode p = dummy;
        int carry = 0;
        while (p1 != null && p2 != null) {
            int val = p1.val + p2.val + carry;
            carry = val / 10;
            val = val % 10;
            p.next = new ListNode(val);
            p = p.next;
            p1 = p1.next;
            p2 = p2.next;
        }
        while (p1 != null) {
            int val = p1.val + carry;
            carry = val / 10;
            val = val % 10;
            p.next = new ListNode(val);
            p = p.next;
            p1 = p1.next;
        }
        while (p2 != null) {
            int val = p2.val + carry;
            carry = val / 10;
            val = val % 10;
            p.next = new ListNode(val);
            p = p.next;
            p2 = p2.next;
        }
        if (carry != 0) {
            p.next = new ListNode(1);
        }
        return reverseList(dummy.next);
    }

    /*
     * 三个指针一起往后走
     */
    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        ListNode temp = null;
        
        while(cur!=null){
            temp = cur.next;
            cur.next=pre;
            pre=cur;
            cur=temp;
        }
        
        return pre;
    }
    /*
     * 如果不允许反转的话
     * 那么可以用stack这种先进后出的数据结构
     * 先全放进去，再拿出来，这样可以达到反转的效果
     * 
     * 注意插入答案的时候
     * 要插入在dummy和之前的答案之间
     * 
     * dummy - 新算出来的答案 - 之前的答案
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        Stack<Integer> stack2 = new Stack<>();
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        ListNode dummy = new ListNode();
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            int val = carry;
            if (!stack1.isEmpty()) {
                val += stack1.pop();
            }
            if (!stack2.isEmpty()) {
                val += stack2.pop();
            }
            carry = val/10;
            val = val % 10;
            ListNode newNode = new ListNode(val);
            newNode.next = dummy.next;
            dummy.next = newNode;
        }
        return dummy.next;

    }
}
