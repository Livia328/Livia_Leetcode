package Java;

import java.util.*;

public class Q94 {
    List<Integer> res = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        dfs(root);
        return res;
    }

    public void dfs(TreeNode cur) {
        // base case
        if (cur  == null) {
            return;
        }
        dfs(cur.left);
        // action
        // add to res
        res.add(cur.val);
        dfs(cur.right);
    }
}