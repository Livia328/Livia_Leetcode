public class Q70 {
    /*
     * dp
     * dp[i]为爬到第i个台阶的ways
     * 因为每次爬的时候可以选择爬1格还是两格
     * 
     * dp[i] = dp[i - 1] + dp[i - 2]
     * base case: dp[0] = 0, dp[1] = 1, dp[2] = 2
     * 
     * 台阶是0的时候，没法走
     * 是1的时候只有一种走法，上1格
     * 是2的时候有两种走法，上2格
     * 
     * 最后返回dp[n]
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        // 要先处理test case，不然dp会越界
        int[] dp = new int[n + 1];
        dp[1] = 1; dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
