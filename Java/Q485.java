public class Q485 {
    /**
     * 遍历数组，用计数器
     * 如果是1就加一，如果是0就计数器清零
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0, curCount = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                curCount++;
            } else {
                // update res
                res = Math.max(res, curCount);
                // reset the curCount
                curCount = 0;
            }
        }
        // 检查最后一个组0
        res = Math.max(res, curCount);
        return res;
    }
}