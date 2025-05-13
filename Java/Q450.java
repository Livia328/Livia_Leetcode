public class Q450 {
    /*
     * root.val == key
     * 合并子树，返回新子树的节点
     * 
     * 删除的时候有四种情况
     * 1. 删除的是leaf
     *    root.left && root.right == null
     *    直接delete，return null
     * 2. root.right == null，直接返回左子树
     * 3. root.left == null，直接返回右子树
     * 4. 左右子树都不为空
     *    需要将右子树中最小的那个做为root
     * 
     * 用一个helper function来获取右子树中最小的
     * -> 也就是一直找到它最左的子树
     * 
     * 2.recursive rule 
        2.1 root.val > key, deleteNode(root.left, key);
        2.2 root.val < key, deleteNode(root.right, key);
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // 获得右子树最小的节点
            TreeNode minNode = getMin(root.right);
            // 把这个最小的节点在右子树中删除
            root.right = deleteNode(root.right, minNode.val);
            // minNode就是这两个子树新的parent
            minNode.left = root.left;
            minNode.right = root.right;
            root = minNode;
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    public TreeNode getMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
