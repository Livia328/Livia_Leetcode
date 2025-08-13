import java.util.ArrayList;
import java.util.Stack;

public class Q1019 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    /*
     * 单调栈
     * 先把linkedlist转化成array
     * 方便通过index访问
     * 
     * 然后我们想象成身高
     * 从后面开始访问
     * 2 1 8 3
     * 每次加入新元素的时候，都把stack里比他小的都
     */
    public int[] nextLargerNodes(ListNode head) {
        ArrayList<Integer> nums = new ArrayList<>();
        for (ListNode p = head; p !=null; p = p.next) {
            nums.add(p.val);
        }
        int[] res = new int[nums.size()];
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.size() - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums.get(i)) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek();
            stack.push(nums.get(i));
        }
        return res;
    }
}
