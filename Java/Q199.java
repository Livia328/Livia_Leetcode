import java.util.*;

public class Q199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            int index = 0;
            for (int i = 0; i < n; i++) {
                TreeNode cur = queue.poll();
                // 确保先加入left
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
                // 如果是这一行最右边那个，那么就是答案
                if (i == n - 1) {
                    res.add(cur.val);
                }
            }
        }
        return res;
    
    }

    /**
     * DFS
     *    1
     *  2   3
     *   5   4
     * 
     * res: 1 3
     */
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        recursion(res, root, 0);
        return res;
    }

    public void recursion(List<Integer> res, TreeNode root, int height) {
        if (root == null) {
            return;
        }
        if (height == res.size()) {
            res.add(root.val);
        }
        recursion(res, root.right, height + 1);
        recursion(res, root.left, height + 1);
    }

}
