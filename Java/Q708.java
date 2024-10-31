public class Q708 {
    class Node {
        public int val;
        public Node next;
    
        public Node() {}
    
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    };

    /*
     * 用pre，cur，next找
     * 因为是一个环，所以要分情况讨论
     * 
     * pre < next, taget是same as pre或者，在pre和next之间，可以插入
     * [1 -> 3]: 那么可以insert 1， 2
     * 
     * pre > next （说明到了环的结尾）,且target >= pre
     * [3 -> 1], target可以和pre一样，或者更高，val = 3,或者4
     * 
     * pre > next, 且tareget <= next
     * [3 -> 1], val = 1或者更小
     */
    public Node insert(Node head, int insertVal) {
        Node insertNode = new Node(insertVal);
        if (head == null) {
            insertNode.next = insertNode;
            return insertNode;
        }
        Node pre = head;
        Node next = head.next;
        while (next != head) {
            // 如果找到位置了，就break
            if ((insertVal < next.val && insertVal >= pre.val) ||
               (pre.val > next.val && pre.val <= insertVal) ||
               (pre.val > next.val && insertVal <= next.val)) {
                break;
            }
            pre = next;
            next = next.next;
        }
        // link
        // pre -> next
        // pre -> insertNode -> next
        pre.next = insertNode;
        insertNode.next = next;
        return head;
    }
}
