import java.util.*;

public class Q346 {
    /*
     * 因为要可以把最早的给移出去，所以queue
     * 先进先出
     */
    class MovingAverage {
        Queue<Integer> queue;
        int size;
        double sum;

        public MovingAverage(int size) {
            queue = new ArrayDeque<>();
            this.size = size;
        }
        
        public double next(int val) {
            if (queue.size() == size) {
                sum -= queue.poll();
            }
            queue.offer(val);
            sum += val;
            return sum / queue.size();
        }
    }
}
