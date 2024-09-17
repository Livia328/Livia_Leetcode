public class Q993 {
    TreeNode parentX = null, parentY = null;
    int depth_x = -1;
    int depth_y = -1;
    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, x, y, null, 0);
        return (parentX != parentY) && (depth_x == depth_y);
    }

    public void dfs(TreeNode root, int x, int y, TreeNode parent, int depth) {
        if (root == null) {
            return;
        }
        if (root.val == x) {
            parentX = parent;
            depth_x = depth;
        }
        if (root.val == y) {
            parentY = parent;
            depth_y = depth;
        }
        dfs(root.left, x, y, root, depth+1);
        dfs(root.right, x, y, root, depth+1);
    }
}
