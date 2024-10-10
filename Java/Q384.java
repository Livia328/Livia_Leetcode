import java.util.Arrays;
import java.util.Random;
public class Q384 {
    /*
     * 重点在于要求：All permutations of the array should be equally likely as a result of the shuffling.
     * 也就是说打乱后一定是n!种可能
     * 
     * 对于 `nums[0]`，我们把它随机换到了索引 `[0, n)` 上，共有 `n` 种可能性；
     * 对于 `nums[1]`，我们把它随机换到了索引 `[1, n)` 上，共有 `n - 1` 种可能性；
     * 对于 `nums[2]`，我们把它随机换到了索引 `[2, n)` 上，共有 `n - 2` 种可能性；
     * 
     * 以此类推，该算法可以生成 `n!` 种可能的结果，所以这个算法是正确的，能够保证随机性
     */
    class Solution {

        private int[] nums;
        private Random random;

        public Solution(int[] nums) {
            this.nums = nums;
            random = new Random();
        }
        
        public int[] reset() {
            return nums;
        }
        
        public int[] shuffle() {
            int n = nums.length;
            int[] copy = Arrays.copyOf(nums, n);
            for (int i = 0; i < n; i++) {
                // 生成一个[i, n - 1]中的随机数
                int r = i + random.nextInt(n - i);
                swap(copy, i, r);
            }
            return copy;
        }

        public void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
