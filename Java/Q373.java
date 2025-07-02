import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Q373 {
    /*
     * 比如[1, 4, 9]和[2, 3, 7]
     * 可以变成两组有序链表
     * 
     * [1, 2] -> [1, 3] -> [1, 7]
     *             p1
     * [4, 2] -> [4, 3] -> [4, 7]
     *  p2
     * [9, 2] -> [9, 3] -> [9, 7]
     *  p3
     * 
     * 比较p1, p2, p3的和
     * p1最小，那么就把p1往后
     * 
     * 这样我们可以在pq里放入三个{nums1[0], nums2[i], i}
     * 这样就知道这个pair来自那个列了
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 按照和从小到大的pq
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> (a[0] + a[1]) - (b[0] + b[1])
        );
        // 放入初始的n个
        for (int i = 0; i < nums1.length; i++) {
            pq.offer(new int[]{nums1[i], nums2[0], 0});
        }
        List<List<Integer>> res = new LinkedList<>();
        while (!pq.isEmpty() && k > 0) {
            int[] cur = pq.poll();
            k--;
            int nextIndex = cur[2] + 1;
            if (nextIndex < nums2.length) {
                pq.add(new int[]{cur[0], nums2[nextIndex], nextIndex});
            }
            List<Integer> pair = new LinkedList<>();
            pair.add(cur[0]);
            pair.add(cur[1]);
            res.add(pair);
        }
        return res;
    }
}
