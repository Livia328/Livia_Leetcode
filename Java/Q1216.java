public class Q1216 {
    /*
     * dp:
     * 
     * 先找到最长回文subsequence
     * leetcode 516
     * 
     * 如果最长回文sequence + k >= s.length
     * 说明可能通过改变剩下的字符将整个s变成palindrome
     * 
     * 找到Longest palindrome subsequence
     * dp
     * 
     * 如何定义dp数组？
     * 在子串s[i..j]中的，最长回文子序列的长度dp[i][j]
     * 如何保证可推出？s[i + 1, j - 1] -> s[i, j], 也就是每次检查s[i]和s[j]是否相等
     * 
     * dp数组定义：在子串[i..j]中，最长回文子序列的长度为dp[i][j]
     * 为什么需要二维数组呢？因为只有二维的情况下，我们知道了dp[i+1][j-1],我们才能推出dp[i][j]的情况
     * 
     * base case: dp[i][i] = 1，因为自己一个数字是palindrome
     * 
     * 状态转移方程：
        arr[i] == arr[j], 那么肯定是最长回文字串的一部分, dp[i][j] == dp[i + 1][j-1] + 2
        arr[i] != arr[j], 说明这两个字符不可能同时出现在subarray中，将其中一个放进去，取(arr[i..j -1],arr[i +1, j])最大的 
        dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j])

     * 由此要得到dp[i][j], 我们需要知道dp[i + 1][j - 1], dp[i + 1][j], dp[i][j - 1]
     * 因此只能从下往上遍历，或者斜着遍历
     * 
     */
    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n][n];
        // base case
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        // 状态转移，反着遍历
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        int lps = dp[0][n - 1];
        return lps + k >= n;
    }
}
