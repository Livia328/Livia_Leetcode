public class Q865 {
    /*
    * 自底向上
    * 
    * 对于一个root来说
    * 如果左子树的depth比右子树大，那么肯定在右子树中
    * 
    * 从下往上找，第一个左右子树深度一样的就是lca deepest
    * 
    * 我们要向上传什么？传以root为根结点的子树的高度，还要传这个树里的最大子节点
    * 
    * 所以需要自定义一个class同时传这两个值
    */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    class Res {
        TreeNode node;
        int depth;

        public Res(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public Res dfs(TreeNode node) {
        if (node == null) {
            return new Res(null, 0);
        }
        Res left = dfs(node.left);
        Res right = dfs(node.right);
        // 因为左子树比右子树高，所以答案肯定在左子树中
        if (left.depth > right.depth) {
            return new Res(left.node, left.depth + 1);
        } 
        // 因为右子树比左子树高，所以答案在右子树中
        if (left.depth < right.depth) {
            return new Res(right.node, right.depth + 1);
        }
        // 两边相等，这就是答案。
        return new Res(node, left.depth + 1);
    }
}
