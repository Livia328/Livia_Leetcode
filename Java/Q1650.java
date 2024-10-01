import org.w3c.dom.Node;

public class Q1650 {
    /*
     * 因为node有parent的信息
     * 类似于找linkedlist的交点
     * 
     * 从P和Q分别出发，让他们走相同的步数
     * 如果一个点走完了，就从另一个点开始
     * 
     * 最后相遇的点就是交点
     */
    public Node lowestCommonAncestor(Node p, Node q) {
        Node i = p, j = q;
        while (i != j) {
            if (i != null) {
                i = i.parent;
            } else {
                i = q;
            }
            if (j != null) {
                j = j.parent;
            } else {
                j = p;
            }
        }
        return i;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };
}
