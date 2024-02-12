import java.util.*;

public class Q314 {
    /**
     * Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).
     * 
     * If two nodes are in the same row and column, the order should be from left to right.
     * 
     * queue,同时放值和col
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        // 同步放treenode和corresponding index
        Queue<TreeNode> queue = new ArrayDeque<>();
        Queue<Integer> cols = new ArrayDeque<>();
        // key: col, value: list of it's 
        Map<Integer, List<Integer>> map = new HashMap<>();
        queue.add(root);
        cols.add(0);
        // 记录最左和最右的值
        int min = 0, max = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int col = cols.poll();
            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<>());
            }
            map.get(col).add(node.val);
            if (node.left != null) {
                queue.add(node.left);
                cols.add(col - 1);
                min = Math.min(min, col - 1);
            }
            if (node.right != null) {
                queue.add(node.right);
                cols.add(col + 1);
                max = Math.max(max, col + 1);
            }
        }
        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }
        return res;
    }
}
