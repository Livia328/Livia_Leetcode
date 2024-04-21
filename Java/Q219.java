import java.util.*;

public class Q219 {
    /**
     * brute force
     * 双重for循环，逐个检查过去
     * TLE
     */
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    if (j - i <= k) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * sliding window, maitain a window length = k
     * 看其中是否有重复的数
     */
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            // 如果加进去的时候发现已经有duplicate了，那么说明是可以的
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
        
    }

    /**
     * hashmap
     * key: number
     * value: most recent index
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 如果这个数曾经出现过，看和上一个index的差是不小于k
            if (map.containsKey(nums[i])) {
                int prv = map.get(nums[i]);
                if (i - prv <= k) {
                    return true;
                }
                // 否则就替换当前index
                // map.put(nums[i], i);
            }
            map.put(nums[i], i);
        }
        return false;
    }

}
