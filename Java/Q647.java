public class Q647 {
    /*
     * 动态规划来判断是否是回文串
     * dp[i][j]表示s[i,j]是否是回文串
     * 
     * basecase是
     * i == j，只有这一个字母的时候，肯定是回文串
     * j == i + 1且s[i] == s[j]，也是回文串
     * 
     * j > i + 1, 那么就要判断dp[i][j] = (s[i] == s[j]) && (dp[i + 1][j - 1])
     * i   j   
     * abcba
     * 
     * 因为我们要根据dp[i + 1][j - 1]来判断dp[i][j]
     * 所以我们要从i右往左，j从上到下，不然有可能出现前一个没计算过的情况
     */
    public int countSubstrings1(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    dp[i][j] = true;
                    count++;
                } else if (j - i == 1 && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    count++;
                } else if (j - i > 1 && s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }
        return count;
    }
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    count++;
                    dp[i][j] = true;
                }
            }
        }
        return count;
    }
}
