public class Q33 {
    /**
     * key point of binary search is like, 
     * how to divide the array into 2 part, 
     * and in which part target is
     * 
     * 
     * one part is sorted, one part is not sorted
     * 4,5,6,7,0,1,2    target 0
     * L           R
     *       M
     * 
     */
    public int search(int[] nums, int target) {
        int L = 0, R = nums.length - 1;
        while (L <= R) {
            int M = L + (R - L) / 2;
            if (nums[M] == target) {
                return M;
            }
            // left part is sorted
            if (nums[L] <= nums[M]) {
                // check weather target is in left part
                if (target >= nums[L] && target <= nums[M]) {
                    R = M - 1;
                } else {
                    L = M + 1;
                }
            } else {
                // right part is sorted
                // check weather target is in the right part
                if (target >= nums[M] && target <= nums[R]) {
                    L = M + 1;
                } else {
                    R = M - 1;
                }
            }
        }
        return -1;
    }
}
