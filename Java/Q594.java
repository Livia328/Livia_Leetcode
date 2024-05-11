import java.util.HashMap;

import javax.print.attribute.HashAttributeSet;

public class Q594 {
    /**
     * 对于每个数x来说，它可以和所有x+1组成sequence
     * 所以我们可以有一个hashmap
     * 然后遍历加起来就可以
     */
    public int findLHS(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = 0;
        for (int key : map.keySet()) {
            if (map.containsKey(key + 1)) {
                res = Math.max(res, map.get(key) + map.get(key + 1));
            }
        }
        return res;
    }
}