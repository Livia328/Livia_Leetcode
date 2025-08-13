import java.util.Stack;

public class Q1475 {
    /*
     * 所以其实重点是要找到，下一个小于等于prices[i]的元素
     * 单调栈
     */
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] res = new int[n];
        int[] nextElement = getNext(prices);
        // 如果存在优惠券，那么就apply
        for (int i = 0; i < n; i++) {
            res[i] = nextElement[i] == -1 ? prices[i] : prices[i] - nextElement[i];
        }
        return res;
    }

    public int[] getNext(int[] nums) {
        int n = nums.length;
        // 存放答案的数组
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() > nums[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return res;
    }
}
