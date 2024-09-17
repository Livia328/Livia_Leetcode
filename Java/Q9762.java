import java.util.Arrays;

public class Q9762 {
    // 贪心，从大往小看
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length - 1; i > 1; i--) {
            if (nums[i] < nums[i - 1] + nums[i - 2]) {
                return nums[i] + nums[i - 1] + nums[i-2];
            }
        }
        return 0;
    }
}
