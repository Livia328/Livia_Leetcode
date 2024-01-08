package Java;

import java.util.*;

public class Q347 {
    public static int[] topKFrequent(int[] nums, int k) {
        // min heap
        // 大顶堆，最上面的是最小的，这样只要维护k个大小的就可以
        // 先计算出frequency
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> map.get(a) - map.get(b));
        for (int cur : map.keySet()) {
            if (pq.size() >= k) {
                if (map.get(cur) > map.get(pq.peek())) {
                    pq.poll();
                    pq.add(cur);
                }
            } else {
                pq.add(cur);
            }
        }
        System.out.println(pq.size());
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll();
        }
        return ans;
    
    }

    public static void main(String[] args) {
        int[] test1 =  {4,1,-1,2,-1,2,3};
        System.out.println(Arrays.toString(topKFrequent(test1, 2)));
    }
}
