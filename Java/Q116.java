import java.util.ArrayDeque;
import java.util.Queue;

import org.w3c.dom.Node;

public class Q116 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
    
        public Node() {}
        
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    /*
    因为是每一层连接，所以BFS顺理成章
    拿到queue里的size，也就是这一层的所有的
    只要不是最后一个，一直往后连接，最后一个连接null
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (i != size - 1) {
                    cur.next = queue.peek();
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return root;
    }
}
