public class Q88 {
    /*
     * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * 
     * Output: [1,2,2,3,5,6]
     * 
     * 因为两个arr都是sort in non-decending order
     * 所以有点像merge sort
     * 
     * 但是因为如果我们从前往后遍历会覆盖掉nums1的前半部分
     * 所以从后往前遍历
     * 
     * 用两个pointer分别指向两个arr的最后一个元素
     * [1,2,3,0,0,0]
     *     p1     index
     * 
     * [2,5,6]
     *     p2
     * 
     * 比较nums1[p1]和nums2[p2]，取大的，填入index
     * 移动被取了的数的指针，移动index指针
     * 
     * 
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1, index = m + n - 1;
        while (p1 >= 0 || p2 >= 0) {
            // 如果有一个走到头了
            if (p1 == -1) {
                nums1[index--] = nums2[p2--];
                continue;
            }
            if (p2 == -1) {
                nums1[index--] = nums1[p1--];
                continue;
            }
            if (nums1[p1] > nums2[p2]) {
                nums1[index--] = nums1[p1--];
                continue;
            }
            nums1[index--] = nums2[p2--];
        }
    }
}
