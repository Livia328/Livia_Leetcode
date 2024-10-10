public class Q426 {
    class Node {
        public int val;
        public Node left;
        public Node right;
    
        public Node() {}
    
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
    /*
     * 因为要sorted，所以肯定是inorder遍历
     * 
     * 两个global变量
     * head = null
     * tail = null
     * 
     * head只有set一遍，if head==null , head = root
     * tail要一直更新，并且负责和新node连接上
     * 
     * tail就是linkedlist的尾巴，node是要接在尾巴后面的node
     * 
     * tail -> node
     *      <-
     * 
     * tail = node
     * 
     * 
     * 结束后，将global变量head和tail相连
     * 
     */
    Node head = null;
    Node tail = null;
    public Node treeToDoublyList(Node root) {
        // corner case
        if (root == null) {
            return null;
        }
        inorder(root);
        // connect head and tail to make a circle
        tail.right = head;
        head.left = tail;
        return head;
    }

    /*
     * 中序遍历
     * 将node和tail连起来
     */
    public void inorder(Node node) {
        // base case
        if (node == null) {
            return;
        }
        inorder(node.left);
        // 只用set head一次
        if (head == null) {
            head = node;
        }
        if (tail != null) {
            tail.right = node;
            node.left = tail;
        }
        tail = node;
        inorder(node.right);
    }
}
