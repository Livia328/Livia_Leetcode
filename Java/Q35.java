public class Q35 {
    /*
     * 二分
     * 寻找左边界
     * 
     * 不存在的时候，要返回nums中大于等于target的最小元素索引
     *  0 1 2 3
     * [2,3,5,7] target = 4
     *  l m   r
     * 
     * m = 1, 3 < 4, l = m + 1 = 2
     * 
     *  0 1 2 3
     * [2,3,5,7] target = 4
     *      l r
     * 
     * m = 2, 5 > 4, r = m - 1 = 1
     * 
     * 跳出循环
     */
    public int searchInsert(int[] nums, int target) {
        int L = 0, R = nums.length - 1;
        // 跳出条件L = R + 1
        while (L <= R) {
            int mid = L + (R - L) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                // shrink the right boundary
                R = mid - 1;
            } else if (nums[mid] < target) {
                L = mid + 1;
            }
        }
        return L;
    }
}
