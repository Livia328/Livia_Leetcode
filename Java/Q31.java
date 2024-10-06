public class Q31 {
    /*
        分析：
     * 我们要找到next permuatation
     * 1,2,3 < 1,3,2
     * 所以我们可以将后面大的数和前面小的数和前面交换，这样就能得到一个大的数
     * 
     * 2. 我们希望这个增加的幅度尽量小，因为我想知道next
     *  1.尽可能在靠近右位交换
     *  2.将一个尽可能小的大数和前面的小数交换，比如 123465，下一个排列应该把 5 和 4 交换而不是把 6 和 4 交换
     *  3.将大数换到前面后，要将大数后面的所有数重制为升序，生序就是最小的
     *      比如123564， 交换了后，将5之后的数都变成声序，即123546
     * 
     * 算法：
     * 1.从后向前，找到第一个相邻生序元素对(i,j),满足a[i]<a[j],此时[j,end]肯定是降序 i就是break point
     * 
     * [left part] i [right part]
     *                这部分是降序的
     * i就是要交换的小数, 123564中就是3
     * 
     * 
     * 2.在j,end中从后向前找到第一个满足a[i]<a[k]的，其中i，k就是小数和大数
     * 将a[i]和a[k]交换，
     * 
     * 可知此时[j,end]必然为降序，逆制成为生序
     * 
     * 如果在1中找不到符合条件的相邻元素，则找不到符合的相邻元素对，说明当前完全是descending
     * 直接reverse
     *      
     * */
    public void nextPermutation(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        int i = nums.length - 2, j = nums.length - 1, k = nums.length - 1;
        // find a[i] < a[j]
        while (i >= 0 && nums[i] >= nums[j]) {
            i--;
            j--;
        }
        // 判断是否找到，如果没找到，为倒叙
        if (i >= 0) { // 不是最后一个序列
            // find a[i] < a[k]
            while (nums[i] >= nums[k]) {
                k--;
            }
            // swap
            int tmp = nums[k];
            nums[k] = nums[i];
            nums[i] = tmp;
        }
        // reverse nums[j, end] 2 pointers
        i = j; j = nums.length - 1;
        while (i < j) {
            int a = nums[i];
            nums[i] = nums[j];
            nums[j] = a;
            i++;
            j--;
        }
    }
}
