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
     * 因为是deep copy，所以需要hashmap将原来的node和新对应上
     * Map<Node, Node> key: original, val: new
     * 
     * 第一次遍历将所有点都copy出来
     * 第二次将random的给连接上
     */
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node p = head;
        while (p != null) {
            map.put(p, new Node(p.val));
            p = p.next;
        }
        p = head;
        while (p != null) {
            Node copy = map.get(p);
            Node copyNext = map.get(p.next);
            copy.next = copyNext;
            Node copyRam = map.get(p.random);
            copy.random = copyRam;
            map.put(p, copy);
            p = p.next;
        }
        return map.get(head);
    }
}
