import java.util.Arrays;

public class Q673 {
    /*
     * 因为我们要知道LIS的个数
     * 所以我们首先要track 以i结尾的subsequence的长度
     * dp[i]
     * 同时我们要track以i结尾的最长subsequence的个数
     * count[i]
     * 
     * 当nums[i] > nums[j]的时候
     * 到nums[i]为止的最长递增子序列长度就变成了dp[i] = dp[j] + 1
     * 
     * 并且因为满足的条件的dp[j]不止一个，所以dp[i]应该是这其中最长的加i
     * 
     * 当nums[i] > nums[j]的时候
     * 如果dp[j] + 1 > dp[i]，说明最长递增子序列的长度增加了，dp[i] = dp[j] + 1，
     * 长度增加，数量不变 count[i] = count[j]，（因为最长的还是count[j]的那些）
     * 
     * 如果dp[j] + 1 == dp[i]，说明最长递增子序列的长度并没有增加
     * 但是出现了新的长度一样的情况，数量增加 count[i] += count[j]
     * 
     */
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n];
        int[] count = new int[n];
        // 最开始的时候，每个字母的最长subsequence就是它自己
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);
        int maxLen = 0;

        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    // 长度增加了
                    if(dp[j] + 1 > dp[i]){
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                        // 长度没变
                    }else if(dp[j] + 1 == dp[i]){
                        count[i] += count[j];
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        int res = 0;
        for(int i = 0; i < n; i++){
            if(dp[i] == maxLen){
                res += count[i];
            }
        }
        return res;
    }
}
