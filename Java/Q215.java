import java.util.*;

public class Q215 {
    /*
     * 维护大小为k个的min heap
     * 每次加入元素后，如果个数超过了k
     * 那么就poll出一个
     * 
     * 到最后pq.peek就是第k个元素
     */
    public int findKthLargest(int[] nums, int k) {
        // min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        for (int num : nums) {
            pq.add(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return (int)pq.peek();
    }
}
