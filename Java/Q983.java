import java.util.*;
import java.util.stream.Collectors;

public class Q983 {
    /*
     * recursion + memo
     * 
     * 假设100天是旅行的最后一天
     * 在100天购买1天通行证，那么接下来需要解决的是1-99天的最小花费
     * 在100天购买7天通行证，那么接下来要解决的是1-93天的最小话费
     * 
     * 可以用recursion的方法解决
     * 如果不是travel day
     * 直接判断它的下一天是不是travel day
     * 
     * dfs(i) = Math.min(dfs(i - 1) + cost[0], 
     *                   dfs(i - 7) + cost[1],
     *                   dfs(i - 30) + cost[2]
     *         )
     * 
     * base case
     * dfs(0) = 0
     */
    public int mincostTickets1(int[] days, int[] costs) {
        // 为了快速判断这天是不是旅行的天
        Set<Integer> isTravel = Arrays.stream(days).boxed().collect(Collectors.toSet());
        int lastDay = days[days.length - 1];
        int[] memo = new int[lastDay + 1];
        return dfs(lastDay, isTravel, costs, memo);
    }

    public int dfs(int curDay, Set<Integer> isTravel, int[] costs, int[] memo) {
        if (curDay < 0) {
            return 0;
        }
        if (memo[curDay] > 0) {
            return memo[curDay];
        }
        if (!isTravel.contains(curDay)) {
            return memo[curDay] = dfs(curDay - 1, isTravel, costs, memo);
        }
        return memo[curDay] = Math.min(dfs(curDay - 1, isTravel, costs, memo) + costs[0],
        Math.min(dfs(curDay - 7, isTravel, costs, memo) + costs[1],
                 dfs(curDay - 30, isTravel, costs, memo) + costs[2]));
    }

    /*
     * dp
     * 
     * 假设100天是旅行的最后一天
     * 在100天购买1天通行证，那么接下来需要解决的是1-99天的最小花费
     * 在100天购买7天通行证，那么接下来要解决的是1-93天的最小话费
     * 
     * 如果不是travel day
     * 直接判断它的下一天是不是travel day
     * 
     * dp(i) = Math.min(dp(i - 1) + cost[0], 
     *                   dp(i - 7) + cost[1],
     *                   dp(i - 30) + cost[2]
     *         )
     * 
     * base case
     * dp[0] = 0
     */
    public int mincostTickets(int[] days, int[] costs) {
        int lastDay = days[days.length - 1];
        Set<Integer> isTravel = Arrays.stream(days).boxed().collect(Collectors.toSet());
        int[] dp = new int[lastDay + 1];
        for (int i = 1; i <= lastDay; i++) {
            if (!isTravel.contains(i)) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = Math.min(dp[i - 1] + costs[0],
                       Math.min(dp[Math.max(i - 7, 0)] + costs[1],
                                dp[Math.max(i - 30, 0)] + costs[2]));
            }
        }
        return dp[lastDay];
    }
}
