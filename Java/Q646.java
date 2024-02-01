import java.util.*;

public class Q646 {
    /**
     * pairs = [[1,2],[2,3],[3,4]]
     * sort according to the pairs[0]
     * 
     * dp:以这个pair结尾的最长chain
     * dp[n]
     * 
     * for each pair[]，去找他前面的pair，看看能不能连上
     * 如果可以的话，那么候选事dp[i], dp[previous] + 1
     */
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a,b) -> a[0] - b[0]);
        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < pairs.length; i++) {
            for (int j = 0; j < i; j++) {
                // 这个能连在之前的上面
                if (pairs[i][0] > pairs[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[pairs.length - 1];
    }
}
