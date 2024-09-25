import java.util.ArrayDeque;
import java.util.Deque;

public class Q2334 {
    /*
     * 单调栈
     * 给一个数，假定这个数是最大的，
     * 我想知道这个数向左向右递减延伸的array
     * 
     */
    public int validSubarraySize(int[] nums, int threshold) {
        int n = nums.length;
        int[] left = new int[n]; // left为左侧小于nums[i]的最近元素位置，不存在的话为-1
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && nums[deque.peek()] >= nums[i]) {
                deque.pop();
            }
            left[i] = deque.isEmpty() ? -1 : deque.peek();
            deque.push(i);
        }
        int[] right = new int[n]; // right为右侧小鱼nums[i]的最近元素位置，不存在的话为n
        deque = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!deque.isEmpty() && nums[deque.peek()] >= nums[i]) {
                deque.pop();
            }
            right[i] = deque.isEmpty() ? n : deque.peek();
            deque.push(i);
        }
        for (int i = 0; i < n; i++) {
            int k = right[i] - left[i] - 1;
            if (nums[i] > threshold/k) {
                return k;
            }
        }
        return -1;
    }
}
