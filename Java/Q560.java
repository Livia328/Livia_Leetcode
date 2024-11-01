import java.util.HashMap;
import java.util.Map;

public class Q560 {
    /*
     * 思路：
     * 暴力解法 -> prefix sum -> preSum + hashmap
     * 
     * 暴力解法，枚举左右边界，时间复杂度到O(n^3)
     */
    public int subarraySum1(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                int sum = 0;
                for (int i = l; i <= r; i++) {
                    sum += nums[i];
                }
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /*
     * 前缀和
     * prefix[n] = suum(0...nums[n - 1])
     */
    public int subarraySum2(int[] nums, int k) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        prefix[0] = 0;
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        int count = 0;
        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                if (prefix[r + 1] - prefix[l] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /*
     * presum + hashMap
     * 因为我们只关心次数
     * 我们可以保存相同前缀和的index有多少个
     * 
     * map<integer, Integer>, key: 前缀和，val: 对应前缀和的个数
     * 我们需要放入put(0,1)表示前缀和为0的时候，有1个
     * 
     * 在遍历的过程中不断得到preSum，不断去map中找 preSum - k
     */
    public int subarraySum3(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int preSum = 0;
        int count = 0;
        for (int num : nums) {
            preSum += num;
            if (map.containsKey(preSum - k)) {
                count += map.get(preSum - k);
            }
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }
}
