public class Q109 {
    /**
     * ecursion每层的action：找出中间的节点，将其作为head
     * 然后对左右两边的linked list进行分治
     * 
     * -2 -> -1 -> 0 -> 1 -> 2
     *            root
     * 
     * 然后分别
     * root.left  = helper(-2 -> -1)
     * root.right = helper(1 -> 2)
     * 
     * -> 这样一看，signature中需要的就是linked list的范围
     * 这里设定为【left, right)寻找中间的
     * 这样就可以很快地寻找中间
     * 
     * 用slow和fast指针寻找中间
     * -10 -> -3 -> 0 -> 5 -> 9 -> right
     *              S 
     *                        F 
    */
    public TreeNode sortedListToBST(ListNode head) {
        return helper(head, null);
    }

    public TreeNode helper(ListNode left, ListNode right) {
        // base case
        if (left == right) return null;
        ListNode mid = findMid(left, right);
        TreeNode root = new TreeNode(mid.val);
        root.left = helper(left, mid);
        root.right = helper(mid.next, right);
        return root;
    }

    public ListNode findMid(ListNode left, ListNode right) {
        ListNode slow = left;
        ListNode fast = left;
        while (fast != right && fast.next != right) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
