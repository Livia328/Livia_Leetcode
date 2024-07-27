public class Q908 {
    public int smallestRangeI(int[] nums, int k) {
        int min = nums[0], max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        return max - min - 2 * k > 0 ? max - min - 2 * k : 0;
    }
}
