import java.util.*;

public class Q545 {
    /*
     * 这题的难点在于keep order和不同种的nodeoverlap
     * 
     * 因为是preOrder traverse的顺序
     * 所以想到一边preOrder traverse一边加入
     * 
     * 2 boolean, isLeftBoundary, isRightBoundary
     * 
     * 
     * 如果node是leftBoundary
     * 那么node.left事leftBoundary
     * node.right可以是leftBoundary, 如果node.left == null 
     * 
     * 同理右子树也是
     */
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
       List<Integer> res = new ArrayList<>();
       if (root != null) {
        res.add(root.val);
        getBoundary(root.left, res, true, false);
        getBoundary(root.right, res, false, true);
       }
       return res;
    }

    public void getBoundary(TreeNode node, List<Integer> res, boolean isLeftBoundary, boolean isRightBoundary) {
        if (node == null) {
            return;
        }
        // 先判断是不是左边界
        if (isLeftBoundary) {
            res.add(node.val);
        }
        // 再判断是不是leave
        if (!isLeftBoundary && !isRightBoundary && node.left == null && node.right == null) {
            res.add(node.val);
        }
        // 判断边界
        // 如果这个树是right bound，且没有右子树，那么它的左子树是right bound
        getBoundary(node.left, res, isLeftBoundary, isRightBoundary && node.right == null);
        getBoundary(node.right, res, isLeftBoundary && node.left == null, isRightBoundary);
        // 因为右边界要等加完了所有左边界和叶子再加
        if (isRightBoundary) {
            res.add(node.val);
        }
    }
}
