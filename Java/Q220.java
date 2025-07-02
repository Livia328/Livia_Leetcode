import java.util.TreeSet;

public class Q220 {
    /*
     * 首先，因为要求index的差要在indexDiff之间
     * 所以我们可以用sliding window来做
     * 
     * 当窗口大小小于等于indexDiff的时候，扩大窗口
     * 当窗口大小大于k的时候，缩小窗口
     * 当窗口大小小于等于k，且存在两个不同元素的diff <= valueDiff的时候
     * 找到答案
     * 
     * 如何在[left, right)中快速判断是否有元素之差小于valueDiff?
     * 可以用treeSet，floor和ceiling的functions
     * 可以快速找到略大于当前值的数和略小于当前值的数
     * 判断他们的diff是不是小于valueDiff
     * 
     * 
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Integer> window = new TreeSet<>();
        int l = 0, r = 0;
        while (r < nums.length) {
            // 因为要保证i != j
            // 所以在把nums[r]放进去前先检查
            Integer ceiling = window.ceiling(nums[r]);
            if (ceiling != null  && (long)ceiling - nums[r] <= valueDiff) {
                return true;
            }
            Integer floor = window.floor(nums[r]);
            if (floor != null && (long)nums[r] - floor <= valueDiff) {
                return true;
            }
            // 扩大窗口，把nums[r]放进去
            window.add(nums[r]);
            r++;
            if (r - l > indexDiff) {
                window.remove(nums[l]);
                l++;
            }
        }
        return false;
    }
}
