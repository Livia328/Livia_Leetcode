package Java;

import java.util.*;

public class Q2 {
    // brute force
    /**
     * 
     * input: nums only contains integers?
     * does it always have answers?
     */
    public int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    ans[0] = i;
                    ans[1] = j;
                    return ans;
                }
            }
        }
        return ans;
    }

    // improve
    // [2,7,11,15]
    // Map: 2,
    //      0 
    public int[] twoSum2(int[] nums, int target) {
        // key is the number value, value is the number's index
        Map<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            // find target - nums[i]
            if (map.containsKey(target - nums[i])) {
                // find the ans
                ans[0] = i;
                ans[1] = map.get(target - nums[i]);
                return ans;
            } 
            map.put(nums[i], i);
        }
        return ans;
    }
}
