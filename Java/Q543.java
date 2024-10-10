public class Q543 {
    /*
     * 有一个全局变量res
     * 每次递归传上来root为根的最长的一条边（Math.max(left, right) + 1）
     * 
     * 在每一层更新res：res = Math.max(res, left + right);
     */
    int res;
    public int diameterOfBinaryTree(TreeNode root) {
        recursion(root);
        return res;
    }

    public int recursion(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = recursion(root.left);
        int right = recursion(root.right);
        res = Math.max(res, left + right);
        return Math.max(left, right) + 1;
    }
    
}
