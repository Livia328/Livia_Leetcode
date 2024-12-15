public class Q1644 {
    /*
     * LCA：
     * 
     * 有2种情况
     *    root
     *   p    q
     * -> root is LCA
     * 
     *    root
     *   p
     * q
     * 
     * -> 如果我们找到了p或者q，因为是先遇到的，那么它一定是LCA
     * 
     * 这题就是可能这两个node不存在
     * 所以我们可以用两个global变量来记录是否found
     * 
     */
    boolean pFound = false;
    boolean qFound = false;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = find(root, p, q);
        if (pFound && qFound) {
            return res;
        }
        return null;
    }

    public TreeNode find(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        TreeNode left = find(root.left, p, q);
        TreeNode right = find(root.right, p, q);
        if (root.val == p.val) {
            pFound = true;
            return root;
        }
        if (root.val == q.val) {
            qFound = true;
            return root;
        }
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}
