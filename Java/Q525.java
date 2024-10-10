import java.util.*;

public class Q525 {
    /*
     * prefix sum
     * 把0看成-1，这样就转换成了
     * 最长的subarray，他们的和是0
     * 
     * 因为是求和，所以想到了prefix sum
     * 
     * 我们要找和为0，也就是要找到index i j， where preSum[i] - preSum[j] == 0
     * -> preSum[i] == preSum[j]
     * 
     * 所以用一个map来存，key是preSum， val是（第一次碰到的）index
     * 因为我们希望这个len越大越好，所以存在map里的index越小越好
     * 所以map里只保存第一个碰到的index
     * 
     */
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + (nums[i] == 0 ? -1 : 1);
        }
        Map<Integer, Integer> valToIndex = new HashMap<>();
        int res = 0;
        for(int i = 0; i < preSum.length; i++) {
            if (!valToIndex.containsKey(preSum[i])) {
                // 第一次出现
                valToIndex.put(preSum[i], i);
            } else {
                // 找到另一个一样的，且这个index肯定比当前index先出现
                res = Math.max(res, i - valToIndex.get(preSum[i]));
                // 因为我们希望这个len越大越好，所以存在map里的index越小越好
                // 所以这个时候不更新，只保存第一个碰到的index
            }
        }
        return res;
    }
}