public class Q100 {
    /*
     * 对于每一个node，返回以这个node为root是不是same tree
     * p，q同时为null，true
     * p，q其中一个为null， false
     * 比较值
     * 返回isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
     */
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
