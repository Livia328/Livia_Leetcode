import java.util.*;

public class Q155 {
    class MinStack {
        // 他们是一一对应存的
        // -1  1  3   -2  normal
        // -1 -1  -1  -2  min
        // 这样可以同时pop出，因为代表着这个数对应的最小值是多少  

        Deque<Integer> normal;
        Deque<Integer> min;

        public MinStack() {
            normal = new ArrayDeque<>();
            min = new ArrayDeque<>();
        }
        
        public void push(int val) {
            normal.push(val);
            if (min.isEmpty()) {
                min.push(val);
            } else {
                // 注意这里，要再push一遍
                min.push(Math.min(min.peek(), val));
            }
        }
        
        public void pop() {
            normal.pop();
            min.pop();
            
        }
        
        public int top() {
            return normal.peek();
        }
        
        public int getMin() {
            return min.peek();
        }
    }
}
