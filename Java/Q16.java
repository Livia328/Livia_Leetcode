import java.util.Arrays;

public class Q16 {
    /*
     * 双指针，类似于3 sum
     * 先排序
     * 
     * [-1,2,1,-4] -> 
     * [-4, -1, 1, 2]
     *  i   L      R
     * 
     * 固定每一个i，L和R对向移动
     * 初始化res为Integer.max，因为每次都要和它取最小的
     * 
     * 
     * 
     */
    public int threeSumClosest1(int[] nums, int target) {
        Arrays.sort(nums);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int L = i + 1, R = nums.length - 1;
            while (L < R) {
                int curSum = nums[i] + nums[L] + nums[R];
                res = Math.abs(res - target) < Math.abs(curSum - target) ? res : curSum;
                if (curSum > target) {
                    // curSum需要变小，所以移动R
                    R--;
                } else if (curSum < target) {
                    L++;
                } else {
                    // 如果等于的话，说明正好是target，可以直接返回了
                    break;
                }
            }
        }
        return res;
    }

    /*
     * 这题并不一定要去除重复的
     * 但去除可以增加一点点效率
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int L = i + 1, R = nums.length - 1;
            while (L < R) {
                int curSum = nums[i] + nums[L] + nums[R];
                res = Math.abs(res - target) < Math.abs(curSum - target) ? res : curSum;
                if (curSum > target) {
                    // curSum需要变小，所以移动R
                    R--;
                    while (R > L && nums[R + 1] == nums[R]) {
                        R--;
                    }
                } else if (curSum < target) {
                    L++;
                    while (R > L && nums[L] == nums[L - 1]) {
                        L++;
                    }
                } else {
                    // 如果等于的话，说明正好是target，可以直接返回了
                    break;
                }
            }
        }
        return res;
    }
}
