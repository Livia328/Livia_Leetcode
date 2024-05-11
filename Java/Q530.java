public class Q530 {
    /**
     * 中序遍历，不断算当前和上一个的val差值，更新res
     * 有一个prv node
     */
    TreeNode prv = null;
    int res = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return res;
    }

    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        // 如果prv是Null，就是把当前值赋值给prv，和最后一样，所以没有额外的操作
        if (prv != null) {
            res = Math.min(res, Math.abs(root.val - prv.val));
        }
        prv = root;
        inorder(root.right);
    }
}
