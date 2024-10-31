public class Q27 {
    /*
     * slow and fast指针
     * 用fast遍历数组，slow表示当前所有不为val的值
     * 
     * 如果fast碰到val，那么就直接跳过
     * 否则将其和nums[slow]交换，slow前进
     * 
     * nums = [3,2,2,3], val = 3
     *         s
     *           f 
     * 
     * nums = [2,3,2,3], val = 3
     *           s
     *             f
     * 
     * nums = [2,2,3,3], val = 3
     *             s
     *               f 
     */
    public int removeElement(int[] nums, int val) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
