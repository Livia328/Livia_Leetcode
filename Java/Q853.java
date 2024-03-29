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

    /**
     * Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
        Output: 3

        int[n][2] //[0]->position [1]->car
        sort -> position
        pos  10 8 5 3 0
        spd   2 4 1 3 1
        time  1 1   7 3   12 ->3 fleet.  7 9 8 
            prelarge -> 12 
            count -> 3

        其实不用stack，只要数着里面有多少个non-asending list就可以了
     */
    public static int carFleet2(int target, int[] position, int[] speed) {
        int[][] cars = new int[position.length][2];
        for(int i=0;i<position.length;i++){
          cars[i][0]=position[i];
          cars[i][1]=speed[i];
        }
        Arrays.sort(cars,(a,b)->b[0]-a[0]);
        float prelarge = 0;
        int count = 0;
        for(int[] car : cars){
          float time = (float)(target-car[0])/car[1];
          if(time>prelarge){
            prelarge = time;
            count++;
          }
        }
        return count;
      }
}
