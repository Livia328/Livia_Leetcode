import java.util.*;

public class Q314 {
    /**
     * Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).
     * 
     * If two nodes are in the same row and column, the order should be from left to right.
     * 
     * 在记录当前node的同时要记录它所在的col
     * 用一个新的数据结构pair，两个attribute，一个col，一个treenode
     * -> 或者用两个queue,一个for treenode， 一个for col
     * 
     * 还要有一个map，key是col，value是lits<Integer> 用来放这一个col对应的所有node的值
     * col的记数从0开始，左边是-1，右边+1，
     * 
     * 怎么从hashmap中加入res中？
     * 可以用treemap
     * 也可以在过程中记录一下最左col和最右col的值分别是什么
     */
    class Pair {
        TreeNode node;
        int col;
        Pair(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int leftCol = 0, rightCol = 0;
        Queue<Pair> queue = new LinkedList<>();
        Pair pair = new Pair(root, 0);
        queue.add(pair);
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            if (!map.containsKey(cur.col)) {
                map.put(cur.col, new ArrayList<>());
            }
            map.get(cur.col).add(cur.node.val);
            if (cur.node.left != null) {
                queue.add(new Pair(cur.node.left, cur.col - 1));
                // 更新左边最小值
                leftCol = Math.min(leftCol, cur.col - 1);
            }
            if (cur.node.right != null) {
                queue.add(new Pair(cur.node.right, cur.col + 1));
                // 更新右边最大值
                rightCol = Math.max(rightCol, cur.col + 1);
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = leftCol; i <= rightCol; i++) {
            res.add(map.get(i));
        }
        return res;
    }


    public List<List<Integer>> verticalOrder1(TreeNode root) {
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
