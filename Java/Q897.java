public class Q897 {
    /*
     * inorder traverse
     * 我们考虑先把左右节点操作完成，再考虑怎么和当前节点连接起来
     * 
     * 把右子树连在当前node的右边
     * 这个可以直接操作
     * root.right = right;
     * 
     * 把根节点连接到左子树的结尾
     * 所以需要有一个p，一路走到左子树的结尾
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