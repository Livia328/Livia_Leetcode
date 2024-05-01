import java.util.*;

public class Q501 {
    /**
     * 暴力：放入map然后计算
     */
    Map<Integer, Integer> map = new HashMap<>();
    public int[] findMode(TreeNode root) {
        inOrder(root);
        int max = Collections.max(map.values());
        List<Integer> res = new ArrayList<>();
        for (int key : map.keySet()) {
            if (map.get(key) == max) {
                res.add(key);
            }
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        inOrder(root.right);
    }

    /**
     * 因为是BST，所以应该想到Inorder遍历
     * 因为要判断当前的数和前一个数是不是一样，所以需要Pointer prv
     * 
     * prv一开始设置成null,这样我们就知道我们遍历的是第一个数
     * 
     * 怎么知道这个数是不是众数？
     * 我们需要记录目前出现的最大频率，maxCount
     * 当count超过MaxCount的时候，将其加入答案
     * 
     * 但是一旦Maxcount被更新了
     * 说明之前的答案全都不作数了，所以要清空答案
     * 
     * 
     */
    TreeNode prv = null;
    List<Integer> res = new ArrayList<>();
    int maxCount = 0;
    int curCount = 0;
    public int[] findMode2(TreeNode root) {
        inOrder2(root);
        return res.stream().mapToInt(i -> i).toArray(); 
    }

    public void inOrder2(TreeNode root) {
        if (root == null) {
            return;
        }
        // 因为是中序遍历，所以先遍历左边
        inOrder2(root.left);
        // 中序操作
        if (prv == null) { // 当前root是第一个root
            maxCount = 1;
            curCount = 1;
            res.add(root.val);
        } else { // 和之前一个比较
            if (root.val == prv.val) { // 如果和之前的一样
                curCount++;
                if (curCount == maxCount) { // root是众数
                    res.add(root.val);
                } else if (curCount > maxCount) { // 更新众数
                    maxCount = curCount;
                    res.clear(); // 清空res，因为之前的全都不是了
                    res.add(root.val);
                }
            }
            if (root.val != prv.val) { // 没有重复
                curCount = 1;
                if (curCount == maxCount) {
                    res.add(root.val);
                }
            }
        }
        // 更新prv
        prv = root;
        inOrder2(root.right);
    }
        
}
