import java.util.Stack;

public class Q84 {
    /**
     * brute force
     * for every bar, keep update
     * 
     * O(n^2), will TLE
     */
    public static int largestRectangleArea(int[] heights) {
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            int curMinBar = heights[i];
            for (int j = i; j < heights.length; j++) {
                curMinBar = Math.min(curMinBar, heights[j]);
                res = Math.max((j - i + 1) * curMinBar, res);
            }
        }
        return res;
    }

    /**
     * 本质上还是去找最右和最左的最大值
     * memo
     */
    public static int largestRectangleArea3(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int n = heights.length;
        int[] left = new int[n]; // index of first bar in the left that is lower than current
        int[] right = new int[n]; // index of first bar in the right that is lower than current
        right[n - 1] = n;
        left[0] = -1;
        for (int i = 1; i < n; i++) {
            int p = i - 1;
            while (p >= 0 && heights[p] >= heights[i]) {
                p = left[p];
            }
            left[i] = p;
        }

        for (int i = n - 2; i >= 0; i--) {
            int p = i + 1;
            while (p < n && heights[p] >= heights[i]) {
                p = right[p];
            }
            right[i] = p;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, (right[i] - left[i] - 1) * heights[i]);
        }
        return res;
    }

    /**
     * 当我们发现有一个柱子比前一个小的话，那么说明这个矩形不能向右拓展了，因此可以计算前面的面积
     * 本质上还是找到向左和向右延展的高度
     * 
     * 为什么要提前放-1？和上面那个一样，因为在计算的面积的时候
     * right的含义是first bar which is lower the current one in the right
     * left的含义是first bar which is bigger than current onw in the left
     * 所以current bar能形成rectangle的面积是right - left -1
     * 
     * 1 4 
     * 1 2 
     */
    public static int largestRectangleArea2(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        stack.push(-1);
        for (int i = 0; i < heights.length; i++) {
            while(stack.peek() != -1 && heights[i] < heights[stack.peek()]) {
                int curIndex = stack.pop();
                int leftMost = stack.peek();
                res = Math.max(res, (i - leftMost - 1) * heights[curIndex]);
            }
            stack.push(i);
        }
        while(stack.peek() != -1) {
            int curIndex = stack.pop();
            int leftMost = stack.peek();
            res = Math.max(res, (heights.length - leftMost - 1) * heights[curIndex]);
        }
        return res;
    }
}
