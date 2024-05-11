import java.util.Arrays;

public class Q628 {
    /*
     * 排序
     * 如果全是非负数或者全是非正数，那么就是最大的三个数乘
     * 如果有负数，那么可能是最大的三个正数，也可能是最小的两个负数和最大正数的乘机
     */
    public int maximumProduct1(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 3] * nums[n - 2] * nums[n - 1]);
    }

    /**
     * 根据分析，我们只需要得到最大的三个数和最小的两个数
     * 因此只要线性扫描就可以了
     */
    public int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int n : nums) {
            if (n < min1) {
                min2 = min1;
                min1 = n;
            } else if (n < min2) {
                min2 = n;
            }

            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }


}
