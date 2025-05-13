import java.util.*;

public class Q101 {
    /*
     * 因为在一层里对称
     * 所以感觉bfs很合适
     * 
     * 关键是加入queue得顺序
     * 每层从queue里poll出两个来比较
     *      1
     *    2    2
     *  3  4  4  3
     * 
     * 比如poll出2(node1)和2(node2)
     * 
     * 先加入node1.left(3),再加入node2.right(3)
     * 再加入node1.right(4),再加入node2.left(4)
     * 
     * 这样确保poll出来的两个应该是相同的
     * 
     * 如果是空的话也要poll进去
     * 
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        // 一定要用linkedlist，arrayDeque不允许放null
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
          TreeNode node1 = queue.poll();
          TreeNode node2 = queue.poll();
          if (node1 == null && node2 == null) {
            continue;
          }
          if (node1 == null || node2 == null) {
            return false;
          }
          if (node1.val != node2.val) 
            return false;
          queue.add(node1.left);
          queue.add(node2.right);
          queue.add(node1.right);
          queue.add(node2.left);
        }
        return true;
    }
}
