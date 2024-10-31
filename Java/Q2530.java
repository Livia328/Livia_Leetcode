import java.util.PriorityQueue;

public class Q2530 {
    /*
     * 题目意思就是加上nums[i]后，把nums[i]变成它的1/3
     * 所以我们可以从大的开始找
     * 然后把它变成1/3
     * 
     * 因为我们要很快知道num[i]/3和别的数的关系
     * 感觉可以用priorityqueue
     * max heap
     * 
     * 把最大的poll出来，然后再把num[i]/3放进去
     * 重复k次
     */
    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
        for (int num : nums) {
            pq.offer(num);
        }
        long res = 0;
        for (int i = 0; i < k; i++) {
            int cur = pq.poll();
            res += cur;
            // 因为是ceil
            pq.offer((cur + 2) / 3);
        }
        return res;
    }
}
