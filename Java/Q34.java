public class Q34 {
    /*
     * 先寻找左边界
     * 然后寻找右边界
     */
    public int[] searchRange(int[] nums, int target) {
        return new int[]{getLeftBound(nums, target), getRightBound(nums, target)};
    }

    public int getLeftBound(int[] nums, int target) {
        // 搜索区间[L,R]
        int L = 0, R = nums.length - 1;
        // 跳出循环条件：L = R + 1
        while (L <= R) {
            int M = L + (R - L) / 2;
            // 下一个搜索区间：[M + 1, R]
            if (nums[M] < target) {
                L = M + 1; 
            } else if (nums[M] > target) {
                R = M - 1;
            } else if (nums[M] == target) {
                // 因为是寻找左边界，所以等于的话我们要shrink right
                // 因为我们想知道有没有更左边的
                R = M - 1;
            }
        }
        // 检查出界情况
        if (L >= nums.length || nums[L] != target) {
            return -1;
        }
        return L;
    }

    public int getRightBound(int[] nums, int target) {
        // 搜索区间[L,R]
        int L = 0, R = nums.length - 1;
        // 跳出循环条件：L = R + 1
        while (L <= R) {
            int M = L + (R - L) / 2;
            // 下一个搜索区间：[M + 1, R]
            if (nums[M] < target) {
                L = M + 1; 
            } else if (nums[M] > target) {
                R = M - 1;
            } else if (nums[M] == target) {
                // 因为是寻找右边界，所以等于的话我们要shrink left
                // 因为我们想知道有没有更右边的
                L = M + 1;
            }
        }
        // 检查出界情况
        if (R < 0 || nums[R] != target) {
            return -1;
        }
        return R;
    }
}
