import java.util.*;

public class Q230 {
    /**
     * inorder traverse get the kth number
     */
    List<Integer> inorderList = new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        inorder(root);
        return inorderList.get(k - 1);
    }

    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        inorderList.add(root.val);
        inorder(root.right);
    }

    /**
     * 改进：节省空间复杂度
     * 因为是第k个数，其实就是第k个加入list的数
     * 也就是说我们可以在中序位置记录这是第几个就行
     */
    int rank = 0;
    int res = 0;
    public int kthSmallest2(TreeNode root, int k) {
        inorder2(root,k);
        return res;
    }

    public void inorder2(TreeNode root, int k) {
        if (root == null) return;
        inorder2(root.left, k);
        rank++;
        if (rank == k) {
            res = root.val;
            return;
        }
        inorder2(root.right, k);
    }
}
