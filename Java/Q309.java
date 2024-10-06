public class Q309 {
    /*
     * 和lc122一样，只是多了冷冻期（冷冻期一天）
     * 
     * 交易次数无穷
     * 
     * dp的状态是什么？天数，持有状体，交易次数(无限)
     * 
     * 
     * 利润和三个状态有关
     * 天数n，当前是否持有（0，1），因为这题没有限制交易次数，所以不受影响
     * dp[i][0]         =    max(dp[i - 1][0],             dp[i - 1][1] + prices[i])
     * 在i天手上没有股票       前一天也没股票，今天不交易              前一天有股票，今天全卖了
     * 
     * dp[i][1]         =    max(dp[i - 1][1],             dp[i - 2][0] - prices[i])
     * 在i天手上没有股票       前一天也有股票，今天不交易         前一天没有股票，今天新买了，这里要从i-2转移，因为有冷冻期
     * 
     * base case
     * i-1 = -1
     * dp[0][0] = 0
     * -> dp[0][0] = max(dp[-1][0], dp[-1][1]+prices[i]) = max(0, -inf + prices[i]) = 0
     * 
     * dp[0][1] = -prices[i]
     * -> dp[0][1] = max(dp[-1][1], dp[-1][0] - prices[i]) = max(-inf, -prices[i]) = -prices[i]
     * 
     * i - 2 = -1
     * dp[i][1] = max(dp[i - 1][1], dp[i - 2][0] - prices[i]); //dp[i - 1][1]是存在的，继续保留就行
     *          = max(dp[i - 1][0], dp[-1][0] - prices[i]) = max(dp[i - 1][0], -prices[i])
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            if (i - 2 == -1) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }
        return dp[n - 1][0];
    }
}
