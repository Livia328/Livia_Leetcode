import java.util.*;

public class Q239 {
    /** 
    * brute force
    keep sliding window, 每次花O(k)的时间找最大值

    O(nK)
    */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        //n = 8, k = 3
        int[] ans = new int[nums.length - k + 1];
        int L = 0, R = k - 1;
        int index = 0;
        while (R <= nums.length - 1) {
            int max = Integer.MIN_VALUE;
            for (int i = L; i <= R; i++) {
                max = Math.max(max, nums[i]);
            }
            ans[index] = max;
            System.out.println("L = " + L + "R = " + R + "max = " + max);
            L++; R++;index++;
        }
        return ans;
    }

    /**
     * improvement 优化每次都要从头开始找的过程
     */
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        int index = 0;
        // store index
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            // remove numbers out of the window
            while (!dq.isEmpty() && dq.peek() < i - k + 1) {
                dq.poll();
            }
            // remove smaller numbers, because they are useless
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }
            dq.offer(i);
            // window里已经有k个元素，可以更新答案
            if (i >= k - 1) {
                ans[index] = nums[dq.peek()];
                index++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(maxSlidingWindow(nums, 3)));
    }
}
