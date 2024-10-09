import java.util.HashMap;
import java.util.Map;

public class Q560 {
    /*
     * prefix sum
     * prefix[n] = sum(nums[0], nums[n - 1])
     */
    public int subarraySum(int[] nums, int k) {
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
     * prefix + map
     * map, key: sum, value: count
     * 
     */
    public int subarraySum2(int[] nums, int k) {
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
