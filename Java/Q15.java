import java.util.*;

public class Q15 {
    /*
     * 固定第一个数，然后L和R头尾
     * -1,0,1,2,-1,-4
     * i  L         R
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                // 去重
                if (l > i + 1 && nums[l] == nums[l - 1]) {
                    l++;
                    continue;
                }
                if (r < nums.length - 1 && nums[r] == nums[r + 1]) {
                    r--;
                    continue;
                }
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    List<Integer> ans = Arrays.asList(nums[i], nums[l], nums[r]);
                    res.add(ans);
                    l++;
                    r--;
                } else if (sum < 0) {
                    l++;
                } else if (sum > 0) {
                    r--;
                }
            }
        }
        return res;
    }
}
