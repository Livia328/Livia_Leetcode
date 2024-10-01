import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Q224 {
    /*
     * 因为有括号，所以想到递归
     * 为了方便，将S中的所有元素转化到queue里
     * 
     * 碰到'(' -> 递归剩下的部分
     * 碰到')' -> break
     * 这样可以保证可以算到括号里的部分
     * 
     * 如何体现乘除要先操作呢？
     * 先遍历一遍，如果是加减就放进stack里，最后再计算
     * 
     * 对于加减，我们就是正常的放进stack里
     * 但是对于乘除，我们立刻算
     * -> 这样我们就要有一个变量来存前一个符号，初始为“+”
     * 
     * 最后再将还留在stack里的数给加起来
     */
    public int calculate(String s) {
        Queue<Character> queue = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            queue.add(c);
        }
        return helper(queue);
    }

    public int helper(Queue<Character> queue) {
        Stack<Integer> stack = new Stack<>();
        // 前一个运算法则，默认是“+”
        char sign = '+';
        int num = 0;
        while (!queue.isEmpty()) {
            char c  = queue.poll();
            if (Character.isDigit(c)) {
                num =  10 * num + c - '0';
            }
            if (c == '(') {
                num = helper(queue);
            }
            // 遇到了下一个运算法则
            if ((!Character.isDigit(c) && c != ' ') || queue.isEmpty()) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                num = 0;
                sign = c;
            }
            // 这个一定要放在最后，这样可以确保前面所有的操作都做完了
            if (c == ')') {
                break;
            }
        }
        int res = 0;
        for (int i : stack) {
            res += i;
        }
        return res;
    }
}
