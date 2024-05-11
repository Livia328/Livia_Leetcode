import java.util.*;

public class Q589 {
    List<Integer> res = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        preOrder(root);
        return res;
    }

    public void preOrder(Node root) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        for (Node nei : root.children) {
            preOrder(nei);
        }
    }

    class Node {
        public int val;
        public List<Node> children;
    
        public Node() {}
    
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

}
