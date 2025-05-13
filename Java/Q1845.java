import java.util.PriorityQueue;

public class Q1845 {
    /*
     * 因为要返回最小的
     * 所以想到priorityqueue
     */
    class SeatManager {

        // min heap
        PriorityQueue<Integer> pq;

        public SeatManager(int n) {
            pq = new PriorityQueue<>();
            // 把他们全都放进去
            for (int i = 1; i <= n; i++) {
                pq.offer(i);
            }
        }
        
        public int reserve() {
            // pop出pq上的
            return pq.poll();
        }
        
        public void unreserve(int seatNumber) {
            // 再放回pq
            pq.offer(seatNumber);
        }
    }
}
