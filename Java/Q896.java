public class Q896 {
    public boolean isMonotonic(int[] nums) {
        boolean increase = true, decrease = true;
        for (int i = 1; i < nums.length; i++) {
            increase &= nums[i - 1] <= nums[i];
            decrease &= nums[i - 1] >= nums[i];
        }
        return increase || decrease;
    }
}
