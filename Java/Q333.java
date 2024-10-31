public class Q333 {
    /*
     * 对于一个node来说，我需要知道下面的node传来
     * 1.以left或者right为root的子树是不是bst
     * 2.size
     * 2.left子树的最大值，因为root val要比左子树的最大值还要大
     * 3.right子树的最小值，因为root.val要比右子树的最小值还要小
     * 
     * 用一个全局变量来记录max
     * 这题要记住，返回到上一层的是当前bst的相关数字
     * 所以一旦不是bst了，size什么的都要清0
     * 
     * 最大的用全局变量要track
     * 
     * 如果当前node可以是BTS的一部分，返回left.num + right.num + 1
     * 否则返回清零
     * 
     * 怎么返回这么多值？
     * 可以定义一个新的class，或者我们可以用int[] arr
     */
    class Res {
        boolean isBST;
        int max;
        int min;
        int size;

        public Res(boolean isBST, int max, int min, int size) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
            this.size = size;
        }
    }
    int max = 0;
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return max;
    }

    public Res helper(TreeNode root) {
        if (root == null) {
            // 因为我们希望它的上一个node比较的时候永远是true
            return new Res(true, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
        }
        if (root.left == null && root.right == null) {
            // 因为我们希望它的上一个node比较的时候永远是true
            max = Math.max(1, max);
            return new Res(true, root.val, root.val, 1);
        }
        //先拿到左右的
        Res left = helper(root.left);
        Res right = helper(root.right);
        if (left.isBST && right.isBST) {
            if (root.val > left.max && root.val < right.min) {
                int size = left.size + right.size + 1;
                max = Math.max(size, max);
                return new Res(true, Math.max(root.val, right.max), Math.min(root.val, left.min), size);
            }
        }
        return new Res(false, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
    }
}
