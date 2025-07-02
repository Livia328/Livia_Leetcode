public class Q1658 {
    /*
     * 第一反应是递归穷举
     * 但是可以问数据量，发现不行
     * 
     * 因为是从数组边缘删掉x
     * 那么剩下来的就是nums的一个子数组
     * 因为要尽可能地少删
     * 所以要让子数组的长度尽可能大
     * 
     * 所以等价于
     * nums中元素为sum(nums) - x的最长子数组
     * 
     * 因为nums里的数都是正数（要向面试官提问）
     * 所以sliding window
     * 
     * 什么时候扩大窗口
     *  当window sum < target的时候扩大
     * 什么时候缩小窗口
     *  当windows sum > target的时候缩小
     * 什么时候更新答案
     *  恰好等于的时候，找到一个符合条件的
     */
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int target = sum - x;
        int left = 0, right = 0;
        int windowSum = 0;
        int maxLen = Integer.MIN_VALUE;
        while (right < nums.length) {
            windowSum += nums[right];
            right++;
            while (windowSum > target && left < right) {
                // 缩小左边界
                windowSum -= nums[left];
                left++;
            }
            if (windowSum == target) {
                maxLen = Math.max(right - left, maxLen);
            }
        }
        return maxLen == Integer.MIN_VALUE ? -1 : n - maxLen;
    }
}
