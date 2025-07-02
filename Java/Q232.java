import java.util.*;

public class Q232 {
    /*
     * 两个stack
     * 比如 放进去 1 2 3
     * stack: 1, 2, 3 (这边是出口)
     * 
     * 要拿出来的时候
     * 拿另一个stack倒腾一下
     * stack: 3, 2, 1
     * 这样就能拿到peak
     * 
     * peek的逻辑：
     * 如果stack2不是空的，那么一直从stack2中pop出
     * 如果是空的，从stack1加入'
     * 
     * pop的逻辑
     * 先peek，保证s2非空，再从s2pop出来
     */
    class MyQueue {
        Stack<Integer> stack1;
        Stack<Integer> stack2;

        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }
        
        public void push(int x) {
            stack1.push(x);
        }
        
        public int pop() {
            peek();
            return stack2.pop();
        }
        
        public int peek() {
            if (stack2.isEmpty()) {
                // 把s1的元素压入s2
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.peek();
        }
        
        // 两个stack都是空的才是空的
        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }
}
