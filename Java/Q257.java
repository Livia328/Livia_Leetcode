import java.util.*;

public class Q257 {
    List<String> res = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return res;
        }
        backtrack(root, "");
        return res;
    }

    public void backtrack(TreeNode root, String prv) {
        // base case
        if (root.left == null && root.right == null) {
            res.add(prv + root.val);
            return;
        }
        if (root.left != null) {
            backtrack(root.left, prv + root.val + "->");
            
        }
        if (root.right != null) {
            backtrack(root.right, prv + root.val + "->");
        }
    }
}
