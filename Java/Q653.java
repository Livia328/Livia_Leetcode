import java.util.*;

public class Q653 {
    /*
     * 中序遍历得到有序数组然后双指针做
     */
    List<Integer> inOrder = new ArrayList<>();
    public boolean findTarget1(TreeNode root, int k) {
        inOrder(root);
        int i = 0, j = inOrder.size() - 1;
        while (i < j) {
            int sum = inOrder.get(i) + inOrder.get(j);
            if (sum == k) {
                return true;
            } else if (sum < k) {
                i++;
            } else if (sum > k){
                j--;
            }
        }
        return false;
    }

    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        inOrder.add(root.val);
        inOrder(root.right);
    }

    /*
     * hashMap + traverse
     */
    Set<Integer> set = new HashSet<>();
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }
}
