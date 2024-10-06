public class Q123 {
    /*
     * lc122的变形，交易次数k == 2
     * 
     * 交易次数无穷
     * 
     * dp的状态是什么？天数，持有状体，交易次数(2)
     * 
     * dp[i][k][0]         =    max(dp[i - 1][k][0],             dp[i - 1][k][1] + prices[i])
     * 在i天手上没有股票       前一天也没股票，今天不交易              前一天有股票，今天全卖了
     * 
     * dp[i][k][1]         =    max(dp[i - 1][k][1],             dp[i - 1][k-1][0] - prices[i])
     * 在i天手上没有股票       前一天也有股票，今天不交易         前一天没有股票，今天新买了，这里要从i-2转移，因为有冷冻期
     * 
     * base case,注意此时的basecase要考虑i和k
     * 
     * 遍历所有k，讲dp[i][k][0]的初始值都赋成下面这个
     * i-1 = -1
     * dp[0][0] = 0
     * -> dp[0][0] = max(dp[-1][0], dp[-1][1]+prices[i]) = max(0, -inf + prices[i]) = 0
     * 
     * dp[0][1] = -prices[i]
     * -> dp[0][1] = max(dp[-1][1], dp[-1][0] - prices[i]) = max(-inf, -prices[i]) = -prices[i]
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int maxK = 2;
        int[][][] dp = new int[n][maxK + 1][2];
        for (int i = 0; i < n; i++) {
            // 为什么从大到小遍历？对于第i天来说，一开始没有交易次数，最大交易限制k应该还是2
            for (int k = maxK; k >= 1; k--) {
                // 对每一个初始状态k都要赋值
                if (i - 1 == -1) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][maxK][0];
    }
}
