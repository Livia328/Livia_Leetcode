import java.util.*;

public class Q1046 {
    public int lastStoneWeight(int[] stones) {
        // max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) {
            pq.add(stone);
        }
        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            if (a - b != 0) {
                pq.add(a - b);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }
}
