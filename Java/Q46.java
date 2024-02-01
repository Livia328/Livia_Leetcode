import java.util.ArrayList;
import java.util.List;

public class Q46 {
    /**
     * distinct number, only once
     * 备选：所有没用过的
     * 所以要有used数组，backtrack里是从0开始
     */
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used);
        return res;
    }

    public void backtrack(int[] nums, boolean[] used) {
        // base case
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // choose
        // 备选是所有没用过的数字
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                backtrack(nums, used);
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}
