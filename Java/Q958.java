import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class Q958 {
    /*
     * 按照层序遍历
     * 如果是complete，所有的数字应该是连在一起的
     * 
     * 因为是一行一行检查，所以bfs
     * 如果碰到num，放入num，如果是空的，放入null
     * 
     * 用一个变量end来判断数字有没有结束
     * end = false
     * 当碰到第一个null的时候，将end变为true
     * 
     */
    public boolean isCompleteTree(TreeNode root) {
        // 一定要用linkedlist
        // 因为arraydeque不支持null
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean end = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    end = true;
                } else {
                    // 因为碰到第一个null后
                    // 说明之后应该都是null
                    if (end) {
                        return false;
                    }
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }
            }
        }
        return true;
    }
}
