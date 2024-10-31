public class Q283 {
    /* 
     * 双指针，slow表示slow之前的都是非0数字
     * [0, slow)
     * 
     * fast表示当前正在traverse的数字
     * 
     * f
     * s
     * 0 1 0 3 12
     * 
     *   f
     *   s
     * 1 1 0 3 12
     * 
     *     f
     *   s
     * 1 1 0 3 12
     * 
     *       f
     *     s
     * 1 3 0 3 12
     * 
     *           f
     *        s
     * 1 3 12 3 12
     * 
     * 然后再把slow开始的数字填为0
     */
    public void moveZeroes(int[] nums) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        // 把slow后面的都变成0
        for(int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}