public class Q965 {
    boolean flag = true;
    int val;
    public boolean isUnivalTree1(TreeNode root) {
        if (root == null) {
            return true;
        }
        val = root.val;
        dfs(root);
        return flag;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.val != val) {
            flag = false;
            return;
        }
        dfs(root.left);
        dfs(root.right); 
    }

    public boolean isUnivalTree2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root, root.val);
    }

    public boolean helper(TreeNode root, int preVal) {
        if (root == null) {
            return true;
        }
        if (preVal != root.val) {
            return false;
        }
        return helper(root.left, root.val) && helper(root.right, root.val);
    }
}
