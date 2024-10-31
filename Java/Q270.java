public class Q270 {
    /*
     * like binary search
     * 每次和root比较，决定走哪一边
     * 
     * cloestVal = root.val
     * minDiff = MAX
     */
    public int closestValue(TreeNode root, double target) {
        int cloestVal = root.val;
        double minDiff = Double.MAX_VALUE;
        while (root != null) {
            double curDiff = Math.abs(root.val - target);
            // 如果更close，或者一样close但是val更小
            // 更新答案
            if (curDiff < minDiff 
            || (curDiff == minDiff && root.val < cloestVal)) {
                cloestVal = root.val;
                minDiff = curDiff;
            }
            if (root.val > target) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return cloestVal;
    }
}
