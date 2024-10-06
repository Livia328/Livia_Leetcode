public class Q714 {
    /*
     * lc122的变形，每次交易都需要交易费，将交易费从利润中减去
     * 
     * 交易次数无穷
     * 
     * dp的状态是什么？天数，持有状体，交易次数(无限)
     * 
     * 一次买卖只需要付一笔交易费用，所以只需要在一次里加减就可以
     * 如果直接把 fee 放在第一个式子里减，会有一些测试用例无法通过，错误原因是整型溢出而不是思路问题。
     * 一种解决方案是把代码中的 int 类型都改成 long 类型，避免 int 的整型溢出。
     * 
     * 利润和三个状态有关
     * 天数n，当前是否持有（0，1），因为这题没有限制交易次数，所以不受影响
     * dp[i][0]         =    max(dp[i - 1][0],             dp[i - 1][1] + prices[i])
     * 在i天手上没有股票       前一天也没股票，今天不交易              前一天有股票，今天全卖了
     * 
     * dp[i][1]         =    max(dp[i - 1][1],             dp[i - 1][0] - prices[i] -fee)
     * 在i天手上没有股票       前一天也有股票，今天不交易         前一天没有股票，今天新买了，这里要从i-2转移，因为有冷冻期
     * 
     * base case
     * i-1 = -1
     * dp[0][0] = 0
     * -> dp[0][0] = max(dp[-1][0], dp[-1][1]+prices[i]) = max(0, -inf + prices[i]) = 0
     * 
     * dp[0][1] = -prices[i]
     * -> dp[0][1] = max(dp[-1][1], dp[-1][0] - prices[i]) = max(-inf, -prices[i] - fee) = -prices[i] - fee
     * 
     * 
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i] - fee;
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
        }
        return dp[n - 1][0];
    }
}
