public class Q671 {
    /*
     * 分治，分别去找左右子树中的第二最小值
     * 因为根节点肯定是最小的
     */
    public int findSecondMinimumValue(TreeNode root) {
        if (root.left == null && root.right == null) {
            return -1;
        }
        int left = root.left.val, right = root.right.val;
        // 去更小的那一个节点里寻找最小第二值
        // 如果左右紫薯都和root的值相同，那么都要去找
        if (root.val == left) {
            left = findSecondMinimumValue(root.left);
        }
        if (root.val == right) {
            right = findSecondMinimumValue(root.right);
        }
        if (left == -1) {
            return right;
        }
        if (right == -1) {
            return left;
        }
        // 如果都找到了，那么更小的就是最小第二值
        return Math.min(left, right);
    }
}
