import java.util.*;

public class Q39 {
    /**
     * backtrack
     * 选择列表：因为可以重复选，所以还是全部candidate
     */
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int pathSum = 0;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) return res;
        backtrack(candidates, target, 0);
        return res;
    }

    public void backtrack(int[] candidates, int target, int start) {
        // add answer
        if (pathSum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (pathSum > target) {
            return;
        }
        // choose and unchoose
        for (int i = start; i < candidates.length; i++) {
            pathSum += candidates[i];
            path.add(candidates[i]);
            // 因为同一个元素可以重复使用，所以还是从i开始
            // 注意这里是i!!!!不是start
            backtrack(candidates, target, i);
            pathSum -= candidates[i];
            path.remove(path.size() - 1);
        }
    }
}
