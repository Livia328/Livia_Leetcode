public class Q814 {
    /*
     * 作为一个node，我要知道我的子树里是不是有1
     * 只有后序位置
     * 
     * 也就是我们先pruneTree(root.left)
     * pruneTree(root.right)
     * 
     * 如果这两个函数return null
     * 说明子树里都是0
     * 
     * 如果root.val == 0 && left == null && right == null
     * return null
     * 
     * 否则就正常return root
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }
}
