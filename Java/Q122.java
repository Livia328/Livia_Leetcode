public class Q122 {
    /*
     * 利润和三个状态有关
     * 天数n，当前是否持有（0，1），因为这题没有限制交易次数，所以不受影响
     * dp[i][0]         =    max(dp[i - 1][0],             dp[i - 1][1] + prices[i])
     * 在i天手上没有股票       前一天也没股票，今天不交易              前一天有股票，今天全卖了
     * 
     * dp[i][1]         =    max(dp[i - 1][1],             dp[i - 1][0] - prices[i])
     * 在i天手上没有股票       前一天也有股票，今天不交易         前一天没有股票，今天新买了
     * 
     * base case
     * dp[0][0] = 0 开始前的日期利润肯定是0
     * dp[0][1] = -prices[i] 当前拥有的现金数是当天股价的相反数
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = - prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }
}
