public class Q543 {
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
