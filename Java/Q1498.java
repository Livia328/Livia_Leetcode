import java.util.Arrays;

public class Q1498 {
    /*
     * 因为是subsequence
     * 所以和顺序无关
     * 我们只关心这其中最小值和最大值
     * 
     * 我们可以sort这个array
     * 然后遍历每一个数字，作为最小值
     * 然后与之匹配的最大值
     * 
     * 满足nums[min] + nums[max] <= target
     * 
     * 对于所有nums[min+1] ~ nums[max]的数
     * 我们都可以选择pick或者不pick
     * 
     * 所以有2 ^ (j - i) subsequences
     * 
     * 具体匹配的时候，用双指针，一个在0，一个在右
     * 对向行走
     */
    private final int MOD = 1000000007;
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0, n = nums.length;
        //record all 2 ^ n to save time
        //一开始可以先不写
        int[] pow = new int[n + 1];
        pow[0] = 1;
        for (int i = 1; i <= n; ++i) {
            pow[i] = (pow[i - 1] * 2) % MOD;
        }

        // binary search寻找max的右边界
        // i为固定的min
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] * 2 > target) break;
            // [L, R), 寻找右边边界
            int L = i, R = nums.length;
            while (L < R) {
                int mid = L + (R - L) / 2;
                if (nums[mid] < target - nums[i]) {
                    L = mid + 1;
                } else if (nums[mid] > target - nums[i]) {
                    R = mid;
                } else if (nums[mid] == target - nums[i]) {
                    // find the right boundary, so we will shrink the left private
                    L = mid + 1;
                }
            }
			// 注意这里要减1，
            L--;
            if (nums[L] + nums[i] <= target) {
                res = (res + pow[L - i]) % MOD;
            }
        }
        return (int)res;
    }
}
