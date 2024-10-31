import java.util.HashMap;

public class Q523 {
    /*
     * prefix sum + hashmap记录状态
     * 
     * 因为是和subarry的和有关，所以很自然的想到prefix sum
     * 
     * 本题让你寻找长度至少为 2 且和为 k 的倍数的子数组，翻译一下就是：
     * 寻找 i, j 使得 (preSum[i] - preSum[j]) % k == 0 且 i - j >= 2。
     * 
     * 另外，(preSum[i] - preSum[j]) % k == 0 其实就是 preSum[i] % k == preSum[j] % k。
     * 所以我们使用一个哈希表，记录 preSum[j] % k 的值以及对应的索引，就可以迅速判断 preSum[i] 是否符合条件
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        // build prefix array
        int n = nums.length;
        int[] prefix = new int[n + 1];
        prefix[0] = 0;
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        // 前缀和与 k 的余数到索引的映射，方便快速查找所需的前缀和
        // key是sum % k, val: index
        HashMap<Integer, Integer> valToIndex = new HashMap<>();
        for (int i = 0; i < prefix.length; i++) {
            // 在哈希表中记录余数
            int val = prefix[i] % k;
            // 如果这个余数还没有对应的索引，则记录下来
            if (!valToIndex.containsKey(val)) {
                valToIndex.put(val, i);
            }
            // 如果这个前缀和已经有对应的索引了，则什么都不做
            // 因为题目想找长度最大的子数组，所以前缀和索引应尽可能小
        }
        int res = 0;
        for (int i = 0; i < prefix.length; i++) {
            int need = prefix[i] % k;
            // 在表里找到同样的余数
            if (valToIndex.containsKey(need)) {
                if (i - valToIndex.get(need) >= 2) {
                    return true;
                }
            }
        }
        return false;
    }
}
