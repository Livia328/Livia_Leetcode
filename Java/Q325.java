import java.util.HashMap;

public class Q325 {
    /*
     * brute force
     * 就是遍历所有的subarray和加起来，
     * 时间复杂度会到O(n^3)
     * 
     * 因为是subarray sum
     * 想到prefix sum
     * 
     * 用一个hashmap，记录preSum[i]的值和这个前缀和第一次出现的索引
     * 就可以迅速判断这个prefix sum是否符合条件以及长度了
     * 
     * 因为我们希望这个array越长越好
     * 所以map里放的是第一次出现这个sum的index
     */
    public int maxSubArrayLen(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> preSumToIndex = new HashMap<>();
        int maxLen = 0;
        int preSum = 0;
        // base case
        // 表示没开始的时候，preSum是0
        // 这样也方便计算长度
        preSumToIndex.put(0, -1);
        for (int i = 0; i < n; i++) {
            preSum += nums[i];
            preSumToIndex.putIfAbsent(preSum, i);
            // preSum[i] - preSum[left] = k
            // need = preSum - k
            int need = preSum - k;
            if (preSumToIndex.containsKey(need)) {
                int left = preSumToIndex.get(need);
                
                maxLen = Math.max(maxLen, i - left);
            }
        }
        return maxLen;
    }
}
