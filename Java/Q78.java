import java.util.*;

public class Q78 {
    /**
     * nums[1,2,3]
     *                   []
     *         [1]       [2]   [3]
     *    [1,2]  [1,3]  [2,3]
     * [1,2,3]
     * 

     * 
     * 
     * path:  
     */
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || res.size() == 0) {
            return res;
        }
        List<Integer> path = new LinkedList<>();
        backtrack(path, nums, 0);
        return res;
    }


    // b(0)
    public void backtrack(List<Integer> path, int[] nums, int start) {
        res.add(new LinkedList<>(path));
        for (int i = start; i < nums.length; i++) { // 2
            // 做选择
            path.add(nums[i]);
            // 向后遍历
            backtrack(path, nums, i + 1);
            // 撤销选择
            path.remove(path.size() - 1);
        }
    }
}
