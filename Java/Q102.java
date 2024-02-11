import java.util.*;

public class Q102 {
    // BFS
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode cur = queue.poll();
                list.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(list);
        }
        return res;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root, 0);
        return res;
    }

    public void helper(List<List<Integer>> res, TreeNode cur, int height) {
        if (cur == null) return;
        if (height >= res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(height).add(cur.val);
        helper(res, cur.left, height + 1);
        helper(res, cur.right, height + 1);
    }
}
