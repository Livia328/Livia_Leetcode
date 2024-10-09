public class Q129 {
    /*
     * 如果树是
     *   1
     * 2   3
     * 
     * 那么第一条路径是12
     * 第一位数是root，所以我们可以有一个preSum从上传递下来
     * 每次preSum * 10 + root.val
     * 
     * 然后再传下去
     */
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int preSum) {
        // base case
        if (root == null) {
            return 0;
        }
        // update presum
        preSum = 10 * preSum + root.val;
        // recursively
        if (root.left == null && root.right == null) {
            return preSum;
        }
        return dfs(root.left, preSum) + dfs(root.right, preSum);
    }
}
