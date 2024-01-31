public class Q153 {
    /*
     * [3,4,5,1,2]
     *        L R  
     *        M
     * 3
     * 
     * 注意，因为以上情况，同样需要更新L，
     * 
     * binary search -> sorted
     * key point: how to divide the array into 2 parts
     * 
     * always one part sorted, one part not sorted
     * 
     * we are going to find the min value, so it must appear in the unsorted part
     * 
     * nums[left] < nums[mid], left is sorted
     *  -> update min
     *  -> shrink the left bound, because we are going to find the unsorted part
     * 
     */
    public int findMin(int[] nums) {
        int L = 0, R = nums.length - 1;
        if (nums[0] < nums[R]) {
            return nums[0];
        }
        int res = Integer.MAX_VALUE;
        while (L <= R) {
            int M = L + (R - L) / 2;
            // 注意这里是小于等于
            // 为什么是小于等于：
            /* [3,4,5,1,2]
            *         L R  
            *         M
            * 
            * 注意，因为以上情况，同样需要更新L，
            */
            if (nums[L] <= nums[M]) {
                res = Math.min(res, nums[L]);
                L = M + 1;
            } else {
                // 右边是sorted，右边最小的事Num[M]，所以candidate事nums[M]
                res = Math.min(res, nums[M]);
                R = M - 1;
            }
        }
        return res;
    }
}
