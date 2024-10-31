import java.util.Stack;

public class Q739 {
    /*
     * 就是要找到这个数之后最近的比这个数大的数
     * 我们当然可以brute force，用双重循环去找
     * 但是这样time complexy 是o(n^2)
     * 
     * 也就是从后往前找，单调栈
     * 
     * stack里面放index
     * 
     * [73,74,75,71,69,72,76,73]
     * [                      0]
     * 
     * stack：
     * 
     * 将76和73比较，因为76 < 73，所以
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = temperatures.length - 1; i >= 0; i--) {
            // 如果当前stack中的值比它小，就pop出来
            // 这样剩下的就是第一个比stack大的数
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        } 
        return res;
    }
}
