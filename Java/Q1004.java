public class Q1004 {
    /*
     * sliding window
     * 记录window中的zero数量
     * zerosInWindow > k的时候，需要shrink左窗口
     */
    public int longestOnes(int[] nums, int k) {
        // [L, R)
        int L = 0, R = 0;
        int zerosInWindow = 0;
        int res = 0;
        while (R < nums.length) {
            int c = nums[R];
            R++;
            if (c == 0) {
                zerosInWindow++;
            }
            while (zerosInWindow > k) {
                int d = nums[L];
                L++;
                if (d == 0) {
                    zerosInWindow--;
                }
            }
            // update res
            res = Math.max(res, R - L);
        }
        return res;
    }
}
