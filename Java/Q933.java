import java.util.LinkedList;
import java.util.Queue;

public class Q933 {
    /*
     * 要动态维护其中的时间，如果大于范围了就poll出去
     * 先进去的要先被poll出来
     * 所以queue比较合适
     */
    class RecentCounter {

        Queue<Integer> queue = new LinkedList<>();

        public RecentCounter() {
            
        }
        
        public int ping(int t) {
            queue.offer(t);
            // 淘汰out date的
            while (queue.peek() < t - 3000) {
                queue.poll();
            }
            return queue.size();
        }
    }
}
