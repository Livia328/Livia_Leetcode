public class Q1448 {
    /**
     * 向下遍历的时候携带这个path上的最大值
     * 如果连最大值都比x小的话，那么肯定都比x小
     *      3
     *    1   4
     *  3    1  5
     */
    int res = 0;
    public int goodNodes(TreeNode root) {
        recursion(root, Integer.MIN_VALUE);
        return res;
    }

    public void recursion(TreeNode root, int maxVal) {
        if (root == null) return;
        if (root.val >= maxVal) {
            // 是good nodes
            res++;
        }
        // 向左向右遍历
        recursion(root.left, Math.max(maxVal, root.val));
        recursion(root.right, Math.max(maxVal, root.val));
    }
}
