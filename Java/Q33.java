public class Q33 {
    /**
     * 重点是找到一个办法让它分成两半
     * 每次分两半，肯定有一半是sort好的，有一半是没sort好的
     * 
     * 如果nums[L] <= nums[M]，说明左半段是sort好的
     *   如果nums[L] <= target <= nums[M]
     *   那么说明target在左半段
     *   否则说明在有半段
     * 
     * 否则说明右半段是sort好的
     *   如果nums[M] <= target <= nums[R]
     *   那么说明target在右半段
     * 
     * one part is sorted, one part is not sorted
     * 4,5,6,7,0,1,2    target 0
     * L           R
     *       M
     * 
     * Input: nums = [4,5,6,7,0,1,2], target = 3
     * Output: 4
     * 
     *  0 1 2 3 4 5 6
     * [4,5,6,7,0,1,2]
     *    l   
     *  r
     *  m
     */
    public int search1(int[] nums, int target) {
        int L = 0, R = nums.length - 1;
        while (L <= R) {
            int M = L + (R - L) / 2;
            if (nums[M] == target) {
                return M;
            }
            // left part is sorted
            if (nums[L] <= nums[M]) {  //
                // check weather target is in left part
                if (target >= nums[L] && target <= nums[M]) {
                    R = M - 1;
                } else {
                    L = M + 1;
                }
            } else if (nums[R] > nums[M]){
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
