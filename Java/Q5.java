import java.util.*;

/**
 * Input: nums = [-1,0,1,2,-1,-4]
   Output: [[-1,-1,2],[-1,0,1]]

   最外面一个数字由for loop确定
   中间的两个数字还是和2sum一样，由2 pointer决定
   注意减枝

   很多小细节要注意
   比如i <= nums.length - 3
   比如while (L < R)
 */
public class Q5 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i <= nums.length - 3; i++) {
            // 提前结束条件
            if (nums[i] + nums[i + 1] + nums[i + 2] > 0) {
                break;
            }
            // 减枝，预防重复答案
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int L = i + 1, R = nums.length - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(nums[i]); cur.add(nums[L]); cur.add(nums[R]);
                    ans.add(cur);

                    while (L < R && nums[L + 1] == nums[L]) L++;
                    while (L < R && nums[R - 1] == nums[R]) R--;
                    L++; R--;
                } else if (sum > 0) {
                    R--;
                } else {
                    L++;
                }
            }
        }
        return ans;
    }
}
