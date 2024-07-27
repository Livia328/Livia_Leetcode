public class Q897 {
    /*
     * inorder
     */
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = increasingBST(root.left);
        root.left = null;
        TreeNode right = increasingBST(root.right);
        root.right = right;
        // 链接左，自己，和右
        // 把根节点连到左子树的结尾
        if (left == null) {
            return root;
        }
        TreeNode p = left;
        while (p != null && p.right != null) {
            p = p.right;
        }
        p.right = root;
        return left;
    }
}