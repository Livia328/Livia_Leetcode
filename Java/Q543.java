public class Q543 {
    /*
     * 对于
     *     1
     *   2  3
     * 4  5
     * 
     * 对于[4]或者5来说，我希望得到0
     * 返回上去的就是1
     * 
     * 对于2来说，左右都返回上来1，这个时候diameter就是 1+1
     * 返回上去就是2
     * 
     * 对于1来说，左边返回上来2，右边返回上来1，diameter就是3
     * 如果再有往上的话就是就应该返回3
     */
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
