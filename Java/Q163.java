import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q163 {
    /*
     * 用target track应该是的数字
     * 一开始target = lower
     * 
     * 如果nums[i] == cur, 那么target++
     * 否则的话，就说明有missing，missing就是[taget, nums[i] - 1]
     * 然后target设置为nums[i] + 1
     * 
     * 最后再检查target是不是小于upper
     */
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        int target = lower;
        List<List<Integer>> res = new ArrayList<>();
        for (int cur : nums) {
            if (cur == target) {
                target++;
            } else{
                res.add(Arrays.asList(target, cur - 1));
                target = cur + 1;
            }
        }
        // check target and upper
        if (target <= upper) {
            res.add(Arrays.asList(target, upper));
        }
        return res;
    }
}
