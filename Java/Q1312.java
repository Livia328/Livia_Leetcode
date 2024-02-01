public class Q1312 {
    /**
     * dp[i][j]: for s[i..j], need min dp[i][j] times to make it palindrome
     * 
     * base case: dp[i][i] = 0, single character is palindrome
     * 
     * how to get the dp[i][j] if we know dp[i+1][j-1]
     * i         j
     * x  a a b  y
     *   i+1  j-1
     * 
     * if s[i] == s[j] -> dp[i][j] = dp[i + 1][j - 1];
     * else 
     *  // we can either modify i or j
     *  dp[i][j] = min(dp[i + 1][j], dp[i][j - 1]) + 1;
     */
    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        // 反向遍历，确保我们已经算出了dp[i + 1][j - 1]
        for (int i = n; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[0][n - 1];
    }
}
