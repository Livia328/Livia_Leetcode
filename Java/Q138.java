import java.util.*;

public class Q138 {
    class Node {
        int val;
        Node next;
        Node random;
    
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * hashtable + 两次遍历
     * 第一次遍历将所有点都copy出来
     * 第二次将random的给连接上
     */
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node p = head;
        while (p != null) {
            Node copy = new Node(p.val);
            map.putIfAbsent(p, copy);
            p = p.next;
        }
        p = head;
        while (p != null) {
            Node next = p.next;
            map.get(p).next = map.get(next);
            Node rand = p.random;
            map.get(p).random = map.get(rand);
            p = p.next;
        }
        return map.get(head);
    }
}
