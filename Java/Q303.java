public class Q303 {
    /*
     * presum
     * preSum array
     * 
     * presum[0] = 0
     *          [1, 2, 3, -1]
     * 
     * presum[0, 1, 3, 6, 5]
     * 
     * presum[i] = presum[i-1] + nums[i - 1]
     */
    class NumArray {
        //前缀和数组
        int[] preSum;
    
        public NumArray(int[] nums) {
            //preSum[0] = 0;
            preSum = new int[nums.length + 1];
            for (int i = 1; i < preSum.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }
        }
        
        public int sumRange(int left, int right) {
            return preSum[right + 1] - preSum[left];
        }
    }
}
