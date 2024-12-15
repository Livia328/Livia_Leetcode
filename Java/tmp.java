import java.util.*;

public class tmp {
    /*
     * 因为BST的中序遍历最后输出的数组就是升序的
     * 在inorder的过程中
     * 用一个glabal变量记录rank
     */
    class Solution {
        int rank;
        int res;
        public int kthSmallest(TreeNode root, int k) {
            inorder(root, k);
            return res;
        }
    
        public void inorder(TreeNode root, int k) {
            if (root == null) return;
            inorder(root.left, k);
            rank++;
            if (rank == k) {
                res = root.val;
                return;
            }
            inorder(root.right, k);
        }
    }
}
