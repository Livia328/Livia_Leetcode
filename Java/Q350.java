import java.util.*;

public class Q350 {
    /**
     * 注意对出现的频率也有要求
     * hashmap
     * 
     * 1: 2
     * 2: 3
     * 
     * 检查map1里的每一个和map2的交集
     * 
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i : nums1) {
            map1.put(i, map1.getOrDefault(i, 0) + 1);
        }Map<Integer, Integer> map2 = new HashMap<>();
        for (int i : nums2) {
            map2.put(i, map2.getOrDefault(i, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        for (int key: map1.keySet()) {
            if (!map2.containsKey(key)) {
                continue;
            }
            int freq = Math.min(map1.get(key), map2.get(key));
            // 往list中加入freq个key
            for (int i = 0; i < freq; i++) {
                res.add(key);
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
