import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;


public class Q117 {
    /*
     * bfs:
     * 
     */
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
     * 和116的区别在于
     * 116是perfect tree
     * 这题就是不好用递归，bfs是一模一样的
     * 
     */
    
}
