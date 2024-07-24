import java.util.Arrays;

public class Q91 {
    /*
     * 感觉是dfs暴力搜索
     * 有两种情况：单独一个数字作为decode，两个数一起作为deocde
     * 时间复杂度会变成O(2^n)
     * 
     * dp recursion + memo改良
     */
    public int numDecodings2(String s) {
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return dp(s.toCharArray(), s.length(), 0, memo);
    }

    public int dp(char[] chars, int length, int index, int[] memo) {
        // 走到头了，是一个答案
        if (index >= length) {
            return 1;
        }
        // 是0的时候跳过，因为0只有作为10这样的时候才是合法的，0，01，都是不合法的
        if (chars[index] == '0') {
            return 0;
        }
        // get from map first
        if (memo[index] != -1) {
            return memo[index];
        }
        // 如果不是0， 那么作为一个数字的时候肯定是合法的
        int res = dp(chars, length, index + 1, memo);
        // 两位数一起被decode的情况
        if (index < length - 1 && (chars[index] == '1' || (chars[index] == '2' && chars[index + 1] <= '6'))) {
            res += dp(chars, length, index + 2, memo);
        }
        memo[index] = res;
        return res;
    }

    /*
     * dp[i]表示s[0...i-1]的解码数
     * 
     */
    public int numDecodings(String s) {
        int n = s.length();
        if (n < 1) {
            return 0;
        }
        int[] dp = new int[n + 1];
        // base case，s为空和s只有一个字符
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= n; i++) {
            char cur = s.charAt(i - 1), prv = s.charAt(i - 2);
            // 当前数本身可以作为一个字母，那么只要看i-1就可以
            if (cur != '0') {
                dp[i] += dp[i - 1]; 
            }
            // 如果可以s[i - 2]和s[i-1]一起作为字母
            if (prv == '1' || (prv == '2' && cur <= '6')) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }
}
