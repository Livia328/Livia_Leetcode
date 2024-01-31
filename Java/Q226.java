public class Q226 {
    /**
     * 分治的思想
     * base case: 如果两个左右都是null， return cur
     * action，将左右子树换了
     * recursive rule：左边右边
     */
    public TreeNode invertTree(TreeNode root) {
        // base case
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * traverse 的思想
     */
    public TreeNode invertTree2(TreeNode root) {
        traverse(root);
        return root;
    }

    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        traverse(root.left);
        traverse(root.right);
    }
}
