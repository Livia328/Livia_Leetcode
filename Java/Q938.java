public class Q938 {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        // 如果目标在右子树
        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        // 这个值在两个之间
        return root.val + rangeSumBST(root.right, low, high) + rangeSumBST(root.left, low, high);

    }
}
