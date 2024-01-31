public class Q98 {
    /**
     * 传node下去
     */
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    /**
     * left: the left bound for cur node
     * right: the right bound for cur node
     */
    public boolean helper(TreeNode cur, TreeNode left, TreeNode right) {
        if (cur == null) return true;
        if (left != null  && cur.val <= left.val) return false;
        if (right != null && cur.val >= right.val) return false;
        return helper(cur.left, left, cur) && helper(cur.right, cur, right);
    }

    /**
     * 传value下去，注意要用long
     */
    public boolean isValidBST2(TreeNode root) {
        return helper2(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean helper2(TreeNode cur, Long left, Long right) {
        if (cur == null) return true;
        if (cur.val <= left) {
            return false;
        }
        if (cur.val >= right) {
            return false;
        }
        return helper2(cur.left, left, (long)cur.val) && helper2(cur.right, (long)cur.val, right);
    }
}
