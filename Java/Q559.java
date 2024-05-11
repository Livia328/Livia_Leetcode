import java.util.*;

public class Q559 {
    /*
     * BFS，遍历了多少层就有多少
     */
    public int maxDepth1(Node root) {
        if (root == null) {
            return 0;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        int res = 0;
        while (!queue.isEmpty()) {
            res++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                Node cur = queue.poll();
                for (Node nei : cur.children) {
                    queue.add(nei);
                }
            }
        }
        return res;
    } 

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int res = 1;
        for (Node nei : root.children) {
            res = Math.max(res, maxDepth(nei) + 1);
        }
        return res;
    }

    // Definition for a Node.
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
