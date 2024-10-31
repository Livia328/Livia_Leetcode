import java.util.*;;

public class Q198 {
    /*
     * dp函数
     * 
     * 对于每个房子有两个选择：抢或者不抢
     * 
     * res = Math.max(dp(nums, start + 1), nums[start], dp(nums, start + 2))
     *                不抢，直接去下一家       抢了这家，去下下架
     */
    private int[] memo;
    public int rob1(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dp(nums, 0);
    }

    private int dp(int[] nums, int start) {
        if (start >= nums.length) {
            return 0;
        }
        if (memo[start] != -1) {
            return memo[start];
        }
        int res = Math.max(dp(nums, start + 1), nums[start] + dp(nums, start + 2));
        memo[start] = res;
        return res;
    }

    /*
     * dp数组的写法
     * 自底向上
     */
    public int rob(int[] nums) {
        int n = nums.length;
        // dp[i] = x表示，从第i间房子开始抢，能抢到的最大钱为x/
        // base case是dp[n] = 0
        int[] dp = new int[n + 2]; // n + 2是根据递推公式来的
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
        }
        return dp[0];
    }
}
