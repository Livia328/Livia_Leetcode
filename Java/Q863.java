import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;

public class Q863 {
    /*
     * 对于每一个节点我们要知道它的parent node
     * 这样就可以同时向上和向左右child扩散
     * 
     * 然后用bfs去找距离为k的
     * 
     * 用一个hashmap记录node和它对应的parent，traverse函数得到每个node的parent
     * node.val唯一吗？唯一的话可以直接用node.val当标识
     */
    // node.val -> parent node
    HashMap<Integer, TreeNode> parent = new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        traverse(root, null);

        // 从target节点开始bfs
        Queue<TreeNode> queue = new ArrayDeque<>();
        HashSet<Integer> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target.val);
        int distance = 0;
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (distance == k) {
                    res.add(cur.val);
                }
                // 向父节点和左右child扩散
                TreeNode parentNode = parent.get(cur.val);
                if (parentNode != null && !visited.contains(parentNode.val)) {
                    visited.add(parentNode.val);
                    queue.offer(parentNode);
                }
                if (cur.left != null && !visited.contains(cur.left.val)) {
                    visited.add(cur.left.val);
                    queue.offer(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right.val)) {
                    visited.add(cur.right.val);
                    queue.offer(cur.right);
                }
            }
            distance++;
        }
        return res;
    }

    public void traverse(TreeNode root, TreeNode parentNode) {
        if (root == null) {
            return;
        }
        parent.put(root.val, parentNode);
        traverse(root.left, root);
        traverse(root.right, root);
    }
}
