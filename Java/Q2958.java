import java.util.*;

public class Q2958 {
    /**
     * sliding window
     * nums = [1,2,3,1,2,3,1,2], k = 2
     * 
     * map to count the frequency
     * 
     * [L, R)
     * 
     * [1,2,3,1,2,3,1,2]
     * L
     *  R
     * 
     * 方法1: 用一个helper function每次去检查是否符合，会TLE
     */
    public int maxSubarrayLength2(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        int L = 0, R = 0;
        int res = 0;
        // [L, R)
        while (R < nums.length) {
            int c = nums[R];
            R++;
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
            while (!validMap(frequency, k)) {
                // shrink left boundary
                int d = nums[L];
                frequency.put(d, frequency.get(d) - 1);
                L++;
            }
            res = Math.max(res, R - L);
        }
        return res;
    }

    public boolean validMap(Map<Integer, Integer> frequency, int k) {
        for (int i : frequency.keySet()) {
            if (frequency.get(i) > k) {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法2: 实际上只要看最新加进来的数就可以
     * 因为我们assume其他在window里的数字都是符合条件的，
     * 那只要让新加进来的数也符合条件就好了
     */
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        int L = 0, R = 0;
        int res = 0;
        // [L, R)
        while (R < nums.length) {
            int c = nums[R];
            R++;
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
            while (frequency.get(c) > k) {
                // shrink left boundary
                int d = nums[L];
                frequency.put(d, frequency.get(d) - 1);
                L++;
            }
            res = Math.max(res, R - L);
        }
        return res;
    }
}
