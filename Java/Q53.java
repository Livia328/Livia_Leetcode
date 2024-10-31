import java.util.*;

public class Q53 {
    /*
     * clarify: sum会越界到long吗
     * 
     * greedy：如果sum负数了
     * 那么就清零从下一个重新开始
     * 
     * 比如如果全是负数
     * -1 -2 -3
     * 最大的和是-1它自己，把他们全都加起来会更小
     */
    public int maxSubArra1y(int[] nums) {
        int curSum = 0;
        // 要设置成最小值，因为有可能sum全是负的
        int maxSum = Integer.MIN_VALUE;
        for (int num : nums) {
            curSum += num;
            maxSum = Math.max(maxSum, curSum);
            if (curSum < 0) {
                curSum = 0;
            }
        }
        return maxSum;
    }

    /*
     * dp:
     * 
     * DP 数组的含义不应该为dp[i]表示nums[0..i] 中的「最大的子数组和
     * 因为subarray是一个continues，我们无法保证nums[0..i]中的最大subarray和nums[i + 1]是相邻的
     * 
     * 所以dp[i]定义为以i结尾的subarry的最大subarray
     * 这样我们可以用dp[i]推出dp[i + 1]
     * 
     * 对于每个数，我们比较它要不要和dp[i - 1]衔接，或者自己作为一个subarray
     * 
     * 最后遍历dp array得到最大的数
     * base case
     * dp[0] = nums[0]
     * 
     * 
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        int res = Integer.MIN_VALUE;
        for (int num : dp) {
            res = Math.max(num, res);
        }
        return res;
    }
}
