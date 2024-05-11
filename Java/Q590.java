import java.util.*;

public class Q590 {
    List<Integer> res = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        postOrder(root);
        return res;
    }

    public void postOrder(Node root) {
        if (root == null) {
            return;
        }
        for (Node nei : root.children) {
            postOrder(nei);
        }
        res.add(root.val);
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
