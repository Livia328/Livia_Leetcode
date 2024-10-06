public class Q1004 {
    /*
     * sliding window
     */
    public int longestOnes(int[] nums, int k) {
        int L = 0, R = 0;
        int res = 0;
        int zerosInWindow = 0;
        while(R < nums.length) {
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
            res = Math.max(res, R - L);
        }
        return res;
    }
}
