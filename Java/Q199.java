import java.util.*;

import Java.TreeNode;

public class Q199 {
    /*
     * 因为是要知道每一层的最右边，所以感觉BFS更合适
     * 按照顺序把node放进queue
     * 每次遍历queue.size个
     * 最后一个就是这一层的最右边
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                // 确保先放入左边的
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
                // 如果是size - 1，说明是这一层的最右边的那个
                if (i == size - 1) {
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
     * 
     * 往下遍历的时候要记录每一层的层数
     * 如果正好到了res.size == height的时候，说明应该加入答案
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
