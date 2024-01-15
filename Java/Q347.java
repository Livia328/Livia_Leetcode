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

    public static int[] topKFrequent2(int[] nums, int k) {
        // count the frequency
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        System.out.println(map);
        // get the frequency in array
        int[][] arr = new int[map.keySet().size()][2];
        int index = 0;
        // arr: frequency n
        for (int n : map.keySet()) {
            arr[index][0] = map.get(n);
            arr[index][1] = n;
            index++;
        }
        // sort the array based on its frequency, in descending order
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = arr[i][1];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] test1 =  {4,1,-1,2,-1,2,3};
        System.out.println(Arrays.toString(topKFrequent2(test1, 2)));
    }
}
