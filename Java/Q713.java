public class Q713 {
    /*
     * 因为里面的nums都是正数（需要和面试官确认）
     * 所以sliding window
     * 
     * 什么时候扩张：prod < k
     * 什么时候收缩：prod >= k
     * 更新答案：一旦我们找到了小于k的
     * 那么这里面所有子数组都是合法子数组
     * 
     * 比方说 left = 1, right = 4 划定了 [1, 2, 3] 这个窗口（right 是开区间）
     * 但不止 [left..right] 是合法的子数组，[left+1..right], [left+2..right] 等都是合法子数组
     * 所以我们需要把 [3], [2,3], [1,2,3] 这 right - left 个子数组都加上
     * 所以里面一共有right - left个subarray
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int L = 0, R = 0;
        int prod = 1;
        int res = 0;
        while (R < nums.length) {
            prod *= nums[R];
            R++;
            while (L < R && prod >= k) {
                prod = prod / nums[L];
                L++;
            }
            // 那么当前是valid的一个window
            // 把所有子数组加入其中
            res += R - L;
        }
        return res;
    }
}
