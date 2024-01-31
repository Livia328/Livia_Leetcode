import java.util.*;

public class Q40 {
    /*
     * 元素重复可重复选的时候，要注意剪枝
     * 也就是先排序，让相同的两个元素靠在一起，如果nums[i] == nums[i-1]，就跳过
     * 也就是同一个值的branch只往下进行一条
     */
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int pathSum = 0;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(candidates, target, 0);
        return res;
    }

    public void backtrack(int[] candidates, int target, int start) {
        if (pathSum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (pathSum > target) {
            return;
        }
        // all option
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.add(candidates[i]);
            pathSum += candidates[i];
            backtrack(candidates, target, i + 1);
            path.remove(path.size() - 1);
            pathSum -= candidates[i];
        }
    }
}
