import java.util.Arrays;

public class Q322 {
    /**
     * backtrack + memo
     * 
     * memo[i]表示amount == i时需要的最少硬币
     */
    public int coinChange(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        int res = backtrack2(coins, amount, memo);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public int backtrack2(int[] coins, int amount, int[] memo) {
        // base case
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (memo[amount] != 0) {
            return memo[amount];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = backtrack2(coins, amount - coin, memo);
            if (res >= 0 && res < min) {
                min = res + 1;
            }
        }
        memo[amount] = (min == Integer.MAX_VALUE ? -1 : min);
        return memo[amount];
    }


    /**
     * dp
     * dp[i]表示凑成i最少需要的硬币数
     * 
     * base case时dp[0] = 0
     * 状态变化：dp[i]取决于dp[i - coin]
     */
    public static int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 也就是取不到的值，不然下面Math.min(dp[i], dp[i - coin] + 1);
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        // 外层走过每一个dp位置，算出答案
        for (int i = 1; i < dp.length; i++) {
            // 每一个状态
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[amount] == amount + 1 ? -1 : dp[amount];

    }

    public static void main(String[] args) {
        int[] test1 = {2};
        System.out.println(coinChange2(test1, 3));
    }
}
