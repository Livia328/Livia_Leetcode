public class Q124 {
    /*
     * 递归
     * 
     * 注意此题重点在于res的值和传上去的值是不一样的：
     * 注意一定要定一个一个全局变量res，res是整个便利过程中出现的最大数
     * 
     * 递归函数定义：经过Node的maxPathSum，
     * res是所有经过过的left + right + node.val的最大值
     * 
     * 传上去的值只有两种情况
     * left + node.val
     * right + node.val
     * 
     * 注意此时对负数的处理
     * 如果left或者right是0，那么肯定小于node.val
     * 取left = Math.max(left, 0)
     * 
     * 如果left和right是负数，最大的就是单独的当前这个数
     */
    int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        // corner case
        if (root == null) {
            return 0;
        }
        dfs(root);
        return res;
    }

    public int dfs(TreeNode root) {
        // base csae
        if (root == null) {
            return 0;
        }
        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);
        res = Math.max(res, left + right + root.val);
        return Math.max(left + root.val, right + root.val);
    }
}
