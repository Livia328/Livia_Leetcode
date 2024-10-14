import java.util.*;

public class Q987 {
    /*
     * 如果是Q314的变种，先根据314的code改一下
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
     * 
     * 
     * 因为如果是same row same col, 根据数据大小排序
     * 
     * key：col， val: 是这个val的所有值
     * 所以在Map<Integer, List<Integer>> 这个里面需要制定规则
     * 
     * 感觉可以放priorityqueue<Pair>, [row, val]
     * -> 先根据row排序，再根据val排序
     */
    class Pair {
        TreeNode node;
        int col;
        int row;
        Pair(TreeNode node, int col, int row) {
            this.node = node;
            this.col = col;
            this.row = row;
        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Map<Integer, PriorityQueue<Pair>> map = new HashMap<>();
        int leftCol = 0, rightCol = 0;
        Queue<Pair> queue = new LinkedList<>();
        int row = 0;
        Pair pair = new Pair(root, 0, row);
        queue.add(pair);
        while (!queue.isEmpty()) {
            row++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                Pair cur = queue.poll();
                if (!map.containsKey(cur.col)) {
                    map.put(cur.col, new PriorityQueue<Pair>((a, b) -> {
                        // 如果行数一样，就按照值从小到大排
                        if (a.row == b.row) {
                            return a.node.val - b.node.val;
                        }
                        // 否则直接按照行数排
                        return a.row - b.row;
                    }));
                }
                map.get(cur.col).add(cur);
                if (cur.node.left != null) {
                    queue.add(new Pair(cur.node.left, cur.col - 1, row));
                    // 更新左边最小值
                    leftCol = Math.min(leftCol, cur.col - 1);
                }
                if (cur.node.right != null) {
                    queue.add(new Pair(cur.node.right, cur.col + 1, row));
                    // 更新右边最大值
                    rightCol = Math.max(rightCol, cur.col + 1);
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = leftCol; i <= rightCol; i++) {
            PriorityQueue<Pair> pq = map.get(i);
            List<Integer> cur = new LinkedList<>();
            while (!pq.isEmpty()) {
                cur.add(pq.poll().node.val);
            }
            res.add(cur);
        }
        return res;
    }
}