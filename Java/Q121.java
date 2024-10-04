package Java;

import javafx.scene.input.PickResult;

public class Q121 {
    // brute force
    // time complexity: O(n^2)
    public static int maxProfit1(int[] prices) {
        int ans = 0;
        for (int i = 0; i < prices.length; i++) {
            int tmp = 0;
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > tmp) {
                    tmp = prices[j];
                }
            }
            ans = Math.max(ans, tmp - prices[i]);
        }
        return ans;
    }

    /*
     * 贪心：
     * 
     * 因为我们知道所有的值，所以我们只要知道在今天之前最小的价格是什么
     * 这样就可以得出目前为止最大的值了
     */
    public static int maxProfit2(int[] prices) {
        //遍历一遍，记录目前经历过的最小值
        int min = prices[0];
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            ans = Math.max(ans, prices[i] - min);
        }
        return ans;
    }

    /*
     * dp：
     * 如果表示状态？
     * 
     * 天数，是否持有股票
     * dp[i][0]表示第i天不持有股票
     * dp[i][1]表示第i天持有股票
     * 
     * dp[i][0] = max(dp[i - 1][0],                     dp[i-1][1] + prices[i])
     *                 今天不交易，意味着今天之前不持有      今天交易，则为前一天持有股票的状态+今天股票的价格
     * dp[i][1] = max(dp[i - 1][1],            - prices[i])
     *           今天不交易，前一天也得持有股票       今天买入股票，因为只能购入一次，所以是-prices[i]
     * 
     * 
     * basecase
     * dp[0][0] = 0
     * -> dp[0][0] = max(dp[-1][0], dp[-1][1] + prices[i])
     *             = max(0, -inf + prices[i]) = 0
     *
     * dp[0][1] = -prices[i]
     * -> dp[0][1] = max(dp[-1][1], dp[-1][0] - prices[i])
     *             = max(-inf, 0 - prices[i]) = -prices[i]
     */
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[n - 1][0];
    }

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit2(prices));
    }
}
