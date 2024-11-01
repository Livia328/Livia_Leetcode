import java.util.ArrayDeque;
import java.util.Queue;

public class Q1609 {
    /*
     * 因为是层序遍历
     * 所以bfs
     * 
     * 用一个boolean记录是奇数层还是偶数层
     * boolean even = true;
     * 
     * 如果奇数层就比较每一个数是不是比前一个数小
     * 如果偶数层就比较每一个数是不是比前一个数大
     * 
     * 因此我们可以统一初始化pre = even ? MIN : MAX
     */
    public boolean isEvenOddTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean even = true;
        while (!queue.isEmpty()) {
            int n = queue.size();
            int pre = even ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                TreeNode cur = queue.poll();
                if (even) {
                    if (pre >= cur.val || cur.val % 2 == 0) {
                        return false;
                    }
                }
                if (!even) {
                    if (pre <= cur.val || cur.val % 2 == 1) {
                        return false;
                    }
                }
                pre = cur.val;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            // 切换奇偶层
            even = !even;
        }
        return true;
    }
}
