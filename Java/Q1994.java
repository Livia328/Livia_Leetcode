import java.util.Stack;

public class Q1994 {
    /*
     * 想到单调栈
     * 因为靠近左边个字高的可以把个子矮的挤掉
     * 所以我们可以从后往前遍历
     * 同时有一个stack，如果stack.peek <= cur
     * 那么就把stack pop
     * 
     * 但是因为要问的是多少个
     * 在pop出去的过程中记录pop出去的人
     * 
     */
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            // 记录右侧比自己矮的人
            int count = 0;
            while (!stack.isEmpty() && heights[i] > stack.peek()) {
                stack.pop();
                count++;
            }
            // 不仅可以看到比自己矮的人，如果后面还有一个高的人，那么可以看到高人
            res[i] = stack.isEmpty() ? count : count+1;
            stack.push(heights[i]);
        }
        return res;
    }
}
