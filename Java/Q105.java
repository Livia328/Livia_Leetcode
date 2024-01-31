import java.io.ObjectOutputStream.PutField;
import java.util.*;

public class Q105 {
    /**
     * 
     * clarify: 都是distinct的吗？
     * 
     * 
     * preorder = [3,9,20,15,7]
     * 
     * inorder = [9,3,15,20,7]
     * 
     * 从preorder里找到第一个数，然后找到inorder里对应的 -> 为了更方便的查找，我们可以建一个map
     * 左边的是左子树，右边的是柚子树
     * 然后我们要recursively build左右子树，然后把他们连上
     * 
     * 因此我们可以分析出build一个数所需的条件
     * preorder, pStart, pEnd, inOrder, iStart, iEnd
     * 
     * 
     * 
     * 
     */
    Map<Integer, Integer> val2index_inorder = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            val2index_inorder.put(inorder[i], i);
        }
        return helper(preorder, 0, preorder.length - 1,
                      inorder, 0, inorder.length - 1);
    }

    public TreeNode helper(int[] preorder, int pStart, int pEnd,
                           int[] inorder, int iStart, int iEnd) {
        // base case
        if (pStart > pEnd) {
            return null;
        }
        // preorder = [3,9,20,15,7]
        // inorder = [9,3,15,20,7]
        TreeNode root = new TreeNode(preorder[pStart]); //root 3
        int rootIndex_inorder = val2index_inorder.get(preorder[pStart]); //1
        int left_subtree_range = rootIndex_inorder - iStart; //1
        TreeNode left = helper(preorder, pStart + 1, pStart + 1 + left_subtree_range - 1, 
                               inorder, iStart, rootIndex_inorder - 1);
        TreeNode right = helper(preorder, pStart + left_subtree_range + 1, pEnd,
                                inorder, rootIndex_inorder + 1, iEnd);
        root.left = left;
        root.right = right;
        return root;
   }
}
