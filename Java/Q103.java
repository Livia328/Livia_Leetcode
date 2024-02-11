import java.util.*;

public class Q103 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        // check the corner case
        if (root == null) {
            return res;
        }
        // start bfs
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean fromLeft = true;
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> level = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                TreeNode cur = queue.poll();
                level.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            if (!fromLeft) {
                Collections.reverse(level);
            }
            res.add(level);
            fromLeft = !fromLeft;
        }
        return res;
    }
}
