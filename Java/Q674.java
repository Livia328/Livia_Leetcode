import java.util.Arrays;

public class Q674 {
    /*
     * 每次将当前数nums[i]和前一个数prv比较
     * 如果nums[i] > prv, 那么更新当前最大长度
     * 
     * 否则curlen清空为1重新开始
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int prv = nums[0];
        int cur = 1;
        int res = 1;
        if (nums.length == 1) {
            return res;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > prv) {
                cur++;
                res = Math.max(res, cur);
            } else {
                cur = 1;
            }
            prv = nums[i];
        }
        return res;
    }

    /*
     * 这题dp的复杂度还不如贪心
     * 
     * DP: 
     * dp[i]为以下标 i 为结尾的数组的连续递增的子序列长度
     * 如果 nums[i]>nums[i−1] ： 
     * nums[i] 可以接在 nums[i−1] 之后（此题要求严格连续递增）
     * 此情况下最长上升子序列长度为 dp[i−1]+1 
     * 
     * basecase是自己就是1
     *     
    */
    public int findLengthOfLCIS1(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

}
