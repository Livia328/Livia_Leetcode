public class Q236 {
    /**
     * 如果他是P或者Q，那么就是LCA
     * 去左右子树里找，如果左右子树都能找到，那么他就是LCA
     * 如果左边没找到，那么就返回从右边返回上来的值
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null) return null;
        if (root.val == q.val || root.val == p.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
}
