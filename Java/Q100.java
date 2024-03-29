public class Q100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // base case
        if (p == null && q == null) {
            return true;
        }
        // if one of them not null, return false;
        if (p == null) {
            return false;
        }
        if (q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
