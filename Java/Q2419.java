public class Q2419 {
    /*
     * 脑筋急转弯
     * 因为bitwise and不会让数字变大
     * (0对应位相与必定为0，1对应位会变为0或者仍为1，综上结果不会变大)
     * 
     * 所以就是统计最大值连续出现了几次
     */
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int max = 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        // res最少也是当前这个数一个
        int res = 1, count = 0;
        for (int num : nums) {
            if (num == max) {
                count++;
            } else {
                // 更新res
                res = Math.max(res, count);
                count = 0; // count清零
            }
        }
        // 最后一段
        res = Math.max(res, count);
        return res;
    }
}
