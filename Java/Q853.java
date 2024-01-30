import java.util.Arrays;
import java.util.Stack;

public class Q853 {
    /**
     * target12
     * 
     *                       10, 2
     *                  8,4
     * 0,1
     *            5,1
     *    3,3
     * 
     *
     * 根据距离目的地的距离，从大到小排序
     * stack里存到达目的地的时间
     * 如果后面车的时间比stack.peek小，说明能追上
     * 
     * 最后返回stack.size()
     */
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        Car[] cars = new Car[n];
        for (int i = 0; i < n; i++) {
            cars[i] = new Car(position[i], speed[i]);
        }
        // 从大到小排
        Arrays.sort(cars, (a,b) -> b.pos - a.pos);
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            double t = (target - cars[i].pos) * 1.0d / cars[i].spe;
            if (!stack.isEmpty() && stack.peek() >= t) {
                // merge
            } else {
                stack.push(t);
            }
        }
        return stack.size();
    }

    class Car {
        int pos;
        int spe;
        public Car(int pos, int spe) {
            this.pos = pos;
            this.spe = spe;
        }
    }
}
