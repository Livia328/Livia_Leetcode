import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Q173 {
    /*
     * inorder遍历完存在list里
     * 然后iterate 这个list
     */
    class BSTIterator {

        private int index;
        private List<Integer> list;

        public BSTIterator(TreeNode root) {
            index = 0;
            list = new ArrayList<>();
            inorderTraverse(root);
        }

        public int next() {
            return list.get(index++);
        }

        public boolean hasNext() {
            return index < list.size();
        }
        
        public void inorderTraverse(TreeNode root) {
            if (root == null) {
                return;
            }
            inorderTraverse(root.left);
            list.add(root.val);
            inorderTraverse(root.right);
        }
    }
}
