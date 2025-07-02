public class Q209 {
    /*
     * 要问nums中的元素是不是都是正数
     * 如果都是正数，那么可以sliding window
     * 
     * 当windos sum >= target的时候缩小窗口
     * 同时更新答案
     */
    public int minSubArrayLen(int target, int[] nums) {
        int l = 0, r = 0;
        int windowSum = 0;
        int res = Integer.MAX_VALUE;

        while (r < nums.length) {
            windowSum += nums[r];
            r++;
            while (windowSum >= target && l < r) {
                res = Math.min(r - l, res);
                windowSum -= nums[l];
                l++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
