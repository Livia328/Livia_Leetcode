import java.util.Arrays;

public class Q977 {
    /*
     * 对于每一个数计算出来，然后再排序
     * time:O(nlogn)
     * space: O(1)
     */
    public int[] sortedSquares1(int[] nums) {
       for (int i = 0; i < nums.length; i++) {
        nums[i] = nums[i] * nums[i];
       }
       Arrays.sort(nums);
       return nums;
    }

    /*
     * 双指针
     * 最大值不是由最左端产生的
     * 就是由最右端产生的
     * 
     * 每次比较最左和最右的平方，然后存入数组
     * 
     * time: O(n)
     * space: O(n)
     */
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int l = 0, r = nums.length - 1;
        int index = nums.length - 1;
        while (l <= r) {
            if (nums[l] * nums[l] < nums[r] * nums[r]) {
                res[index--] = nums[r] * nums[r];
                r--;
            } else {
                res[index--] = nums[l] * nums[l];
                l++;
            }
        }
        return res;
    }
}
