import java.util.Arrays;

public class Q300 {
    /*
     * DP:
     * 
     * dp[i]表示以nums[i]这个数结尾的最长递增序列的长度
     * base case: dp[i] = 1
     * 如果只有它自己，那么肯定是1
     * 
     * 要返回的res应该是dp[]的最大值
     * 
     * 转移方程：
     * 如果是dp[5]，假设nums[5] = 5
     * 所以我们可以把它接在nums[4]之前所有比5小的数后面
     * 
     * dp[5] = Math.max(dp[0]...dp[5]) + 1
     * 
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            // 要找到[0..i]中nums[j]比nums[i]小的最大dp值
            for (int j = 0; j < i; j++) {
                // 说明nums[i]可以接在nums[j]后面
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        // 最后的答案是dp数组中最大的
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
