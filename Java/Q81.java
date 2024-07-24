public class Q81 {
    /*
     * 二分，sorted了之后,肯定有一半是sorted的
     */
    public boolean search(int[] nums, int target) {
        // search area: [L, R]
        int L = 0, R = nums.length - 1;
        // out condition: L = R + 1
        // case L == R, it is [L, L], need one more check
        while (L <= R) {
            // 在计算mid之前要去重
            while (L < R && nums[L + 1] == nums[L]) {
                L++;
            }
            while (L < R && nums[R] == nums[R - 1]) {
                R--;
            }
            int M = L + (R - L) / 2;
            // find the sorted part
            if (nums[M] == target) {
                return true;
            }
            if (nums[L] <= nums[M]) {
                // 前半段有序
                if (nums[L] <= target && target < nums[M]) {
                    // target在有序范围内， 收缩右边界
                    R = M - 1;
                } else {
                    L = M + 1;
                }
            } else {
                // 后半段有序
                if (nums[M] < target && target <= nums[R]) {
                    L = M + 1;
                } else {
                    R = M - 1;
                }
            }
        }
        return false;
    }
}
