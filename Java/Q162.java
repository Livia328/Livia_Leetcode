public class Q162 {
    /*
     * 思路总结：
     * 暴力解法 —> binary search
     * 讲道理，这题直接想到binary search的人有点毛病
     * 
     * 暴力解法，直接遍历
     */
    public int findPeakElement1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            boolean isPeak = true;
            if (i - 1 >= 0) {
                if (nums[i - 1] >= nums[i]) {
                    isPeak = false;
                }
            }
            if (i + 1 < n) {
                if (nums[i + 1] >= nums[i]) {
                    isPeak = false;
                }
            }
            if (isPeak) {
                return i;
            }
        }
        return -1;
    }

    /*
     * binary search
     * 根据num[mid]和nums[mid +- 1]的关系
     * 可以保证一边必然有解，一边必然没解
     */
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        //因为一定有解，所以设定条件l < r
        //跳出循环条件 l == r
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] > nums[m + 1]) {
                r = m; // 因为m还可能是答案
            } else {
                l = m + 1; // 因为nums[m] <= nums[m + 1]，所以m也不可能是答案
            }
        }
        return l;
    }
}
