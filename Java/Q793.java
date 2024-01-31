import java.util.*;

public class Q793 {
    /**
     * 注意：从后往前加入
     * 
     * stack里存的是index
     * 
     * 每次比较cur element和stack peek，如果cur element比较大就pop
     * 永远保证stack.peek是next warmer day
     * 如果stack是空的，说明未来没有更暖和的天了
     * 
     * 
     * 当你可以push的时候，你就可以找到next warmer day了
     * 
     * Input: temperatures = [73,74,75,71,69,72,76,73]
        Output: [1,1,4,2,1,1,0,0]

        input: int[], is null, length is 0
        [73,74,75,71,69,72,76,73]
        res[.        2  1  1  0  0]

        determine the ans when push element
        stack 


        71 3
        72 5
        76 6
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        // corner case
        if (temperatures == null) {
          return null;
        }
        if (temperatures.length == 0) {
          return new int[]{};
        }
        int[] res = new int[temperatures.length];
        // store the index
        Stack<Integer> stack = new Stack<>();
        for (int i = temperatures.length - 1; i >= 0; i--) {
          // compare the element and pop
          while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
            stack.pop();
          }
          if (stack.isEmpty()) {
            res[i] = 0;
          } else {
            res[i] = stack.peek() - i;
          }
          stack.push(i);
        }
        return res;
      }
}
