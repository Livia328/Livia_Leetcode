public class Q410 {
    /*
     * 也就是说分成m个subarray
     * subarray的和要尽可能小
     * 
     * 二分搜素
     * 和那个船运货物的题目是差不多的感觉
     * 
     * 对subarray sum进行二分搜索 mid
     * 用一个函数求，maxSum为mid时，需要分成几个subarray
     * 
     * 如果 > k, 那么分组多了，每个组里的和小了，说明和需要增大
     * 如果 < k，说明分组分少了，每个组里的和多了，说明和需要减小
     * 等于的话，因为我们要求左边界，所有right = mid - 1
     * 
     * 因为进行搜索的是和
     * 所以搜索区间是[max(nums), sum(nums)]
     */
    public int splitArray(int[] nums, int k) {
        int l = 0, r = 0;
        for (int num : nums) {
            l = Math.max(l, num);
            r += num;
        }
        // [l, r]
        // 跳出条件是l = r + 1
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (getDiv(nums, m) < k) {
                r = m - 1;
            } else if (getDiv(nums, m) > k) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return l;
    }

    /*
     * 返回需要分成几个group
     */
    public int getDiv(int[] nums, int targetSum) {
        int group = 1;
        int sum = 0;
        for (int num : nums) {
            if (sum + num > targetSum) {
                group++;
                sum = num;
            } else {
                sum += num;
            }
        }
        return group;
    }
}
