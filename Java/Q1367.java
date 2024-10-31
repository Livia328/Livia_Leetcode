import java.util.ArrayDeque;
import java.util.Queue;

public class Q1367 {
    /*
     * 遍历head，当head.val == root.val的时候
     * 开始用新函数检查能否将root一个个吻合
     * 可以bfs遍历tree的每一个node
     * 
     * check函数
     * 如果root.val == head.val，我们就检查下一个
     * return check(head.next, root.left) || check(head.next, root.right)
     */
    public boolean isSubPath1(ListNode head, TreeNode root) {
        // base case
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // 可以开始检查链表
            if (node.val == head.val) {
                // 如果找到了，直接true
                if (check(head, node)) {
                    return true;
                }
            }
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return false;
    }

    public boolean check(ListNode head, TreeNode node) {
        if (head == null) return true;
        if (node == null) return false;
        if (head.val == node.val) {
            return check(head.next, node.left) || check(head.next, node.right);
        }
        return false;
    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        if (root.val == head.val) {
            if (check(head, root)) {
                return true;
            }
        }
        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }
}
