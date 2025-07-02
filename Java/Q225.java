import java.util.*;

public class Q225 {
    /**
     * 用两个queue
     * 每次要push的时候，都放在q2里
     * 然后把1中原有的元素颠倒过来放在3的后面
     * 
     * top ----------------------------rear
     * 
     * push1
     * queue2 = [1]
     * queue1 = [1] queue2 = [] queue2清空
     * 
     * push2
     * queue2[2]
     * 把queue1里的倒腾到2的后面
     * queue2[2，1]
     * 
     * 再把他们弄会queue1
     * queue1: [2, 1]
     * 
     * push3
     * queue2[3]
     * 把queue1里的弄到queue2
     * [3, 2, 1]
     * 
     * 再搬运到1里
     * [3, 2, 1]
     * 
    */
    class MyStack {
        private Queue<Integer> q1, q2;

        public MyStack() {
            q1 = new LinkedList<>();
            q2 = new LinkedList<>();
        }
        
        public void push(int x) {
            q2.offer(x);
            while (!q1.isEmpty()) {
                q2.offer(q1.poll());
            }
            while (!q2.isEmpty()) {
                q1.offer(q2.poll());
            }
        }
        
        public int pop() {
            return q1.poll();
        }
        
        public int top() {
            return q1.peek();
        }
        
        public boolean empty() {
            return q1.isEmpty();
        }
    }
}
