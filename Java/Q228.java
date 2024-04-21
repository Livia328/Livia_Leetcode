import java.util.*;

public class Q228 {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        if (nums.length == 1) {
            ans.add(nums[0] + "");
            return ans;
        }
        for (int i = 0; i < nums.length; i++) {
            int startNum = nums[i];
            // 向右拓展寻找边界
            while (i + 1 < nums.length && (nums[i + 1] == nums[i] + 1)) {
                i++;
            }
            // 这个区间只有一个数
            if(startNum == nums[i]) {
                ans.add(startNum + "");
            } else {
                ans.add(startNum + "->" + nums[i]);
            }
        }
        return ans;
    }
}
