import java.util.PriorityQueue;

public class Q973 {
    /*
     * priorityqueue
     * 
     * 大顶堆（因为距离越大越远）
     * 里面放int[],根据距离从大到小排序
     * 
     * 如果pq里的个数不足k，就直接放入
     * 如果pq里的个数大于k，和peek元素比较
     * 
     */
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]));
        for(int[] p : points ) {
            // 如果元素不足k个，直接放入
            if (pq.size() < k) {
                pq.offer(p);
            } else {
                // pq的元素大于k个，比较
                if (pq.comparator().compare(p, pq.peek()) > 0) { // 应该放入p
                    pq.poll();
                    pq.offer(p);
                }
            }
        }
        int[][] res = new int[k][2];
        int index = 0;
        while (!pq.isEmpty()) {
            res[index++] = pq.poll();
        }
        return res;
    }
}
