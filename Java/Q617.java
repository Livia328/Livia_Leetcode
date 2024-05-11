public class Q617 {
    /**
     * 分解问题的思路：
     * 定义：输入两个node,返回合并后的node
     */
    public TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {
        // base case
        // 如果一颗树是空的，那么合并后就是另一颗树
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        // operation
        root1.val += root2.val;
        // recursion
        root1.left = mergeTrees1(root1.left, root2.left);
        root1.right = mergeTrees1(root1.right, root2.right);
        return root1;
    }
}
