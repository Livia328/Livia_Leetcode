import java.util.*;

public class Q872 {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaf1 = new ArrayList<>();
        List<Integer> leaf2 = new ArrayList<>();
        getLeaf(root1, leaf1);
        getLeaf(root2, leaf2);
        return leaf1.equals(leaf2);
    }

    public void getLeaf(TreeNode cur, List<Integer> leaf) {
        if (cur == null) {
            return;
        }
        if (cur.left == null && cur.right == null) {
            leaf.add(cur.val);
        }
        getLeaf(cur.left, leaf);
        getLeaf(cur.right, leaf);
    }
}
