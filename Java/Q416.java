import java.util.*;

public class Q416 {
    /*
     * backtrack + memo
     * 
     * 对于每一个数，我们可以选，也可以不选
     * 记录状态，状态是当前和和index
     * 用map来记录
     */
    Map<String, Boolean> memo = new HashMap<>();
    public boolean canPartition1(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        return backtrack(nums, 0, 0, sum / 2);
    }

    public boolean backtrack(int[] nums, int start, int curSum, int target) {
        String key = curSum + "_" + start;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (curSum > target) {
            return false;
        }
        if (curSum == target) {
            return true;
        }
        for (int i = start; i < nums.length; i++) {
            curSum += nums[i];
            boolean flag = backtrack(nums, i + 1, curSum, target);
            memo.put(key, flag);
            if (flag) {
                return true;
            }
            curSum -= nums[i];
        }
        return false;
    }

    /*
     * dp 背包问题
     * 
     * 背包的容量是sum/2，我们有nums物品，每个重量是nums[i]
     * 能否装物品让背包恰好装满
     * 
     * 我们要做的选择就是这个物品要不要装进背包
     * 状态是，目前遍历了多少个物品，背包的剩余空间是多少
     * 
     * dp[i][j]，i表示前i个物品，j表示容量是j的时候
     * 若为true，则表示可以正好装满
     * 
     * 状态转移：
     * 如果不把nums[i]加入背包，那么直接取决于dp[i - 1][j]
     * 如果选择装入nums[i]，那么取决于dp[i - 1][j - nums[i]]
     * 
     * base case：
     * dp[i][0] = true
     * 容量是0的时候肯定是true，不选就行了
     * 
     * 最后要返回的是dp[nums.length][sum];
     * 所以dp函数要偏移一位
     * boolean[][] dp = new boolean[nums.length + 1][sum + 1];
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];
        // base case
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }
        // 开始dp
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= sum; j++) {
                // 可以写完下面的转移方程
                // 再回来写这里的
                if (j - nums[i - 1] < 0) {
                    // 容量不足，不能装入第i个物品
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 可以选择装入或者不装入，只要有一个是true就行
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][sum];
    }
}
