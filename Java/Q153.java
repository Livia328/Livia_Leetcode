public class Q153 {
    /*
     * 这个题的重点是数组分两半
     * 一半是有序的
     * 
     * 可以靠判断nums[L]和nums[M]判断这个时候哪边是sorted
     * 最小值一定在unsorted的part
     * 
     * nums[left] < nums[mid], 左边sorted
     *    candidate是nums[left]，所以更新一下res
     *    同时我们要去找unsorted的部分，所以缩小左边界
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
