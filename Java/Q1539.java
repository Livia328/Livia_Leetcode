public class Q1539 {
    /*
     * 和1060很像
     * 和1060的区别是1060是从arr里的第一个数开始算
     * 这道题是从1开始算
     * 
     * brute force
     * 从1开始遍历，一直递减k
     * 如果k == 0了，说明k在这个里面
     * 
     * 如果到最后k还是大于0，返回最后一个数+k
     */
    public int findKthPositive1(int[] arr, int k) {
        int prev = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != prev + 1) { //我们期待arr[0]是1,也满足
                for (int j = prev + 1; j < arr[i]; j++) {
                    k--;
                    if (k == 0) {
                        return j;
                    }
                }                
            }
            prev = arr[i];
        }
        return arr[arr.length - 1] + k;
    }

    /*
     * binary search
     * 
     * 当前数组    arr = [2, 3, 4, 7, 11]
     * 应当出现    exp = [1, 2, 3, 4, 5]    (即第i个位置本该出现的值)
     * 计算差值   diff = [1, 1, 1, 3, 6]  
     * 
     * diff[i] = arr[i] - (i + 1)
     * 
     * diff[i]为arr[i]之前被拿走了几个数
     * 所以我们就等于要找到第一个大于等于k的坐标
     * 
     * binary search
     * 
     * 我们知道在nums[R]位置上的missing数 = arr[R] - (R + 1)
     * 此时看看还有没有缺的数，k - missing = k - arr[R] + (R + 1)
     * 所以返回的是arr[R] + k - arr[R] + (R + 1) = k + R + 1
     */
    public int findKthPositive(int[] arr, int k) {
        //[L, R]
        int L = 0, R = arr.length - 1;
        // L = R + 1
        while (L <= R) {
            int M = L + (R - L) / 2;
            int missing = arr[M] - M - 1;
            if (missing < k) {
                L = M + 1; //缩短左边界
            } else if (missing > k) {
                R = M - 1; //缩短右边界               
            } else if (missing == k) {
                // 因为要找的是第一个大于等于k的
                // 所以我们要缩小右边界去看看还有没有更小的
                R = M - 1;
            }
        }
        // 我们知道在nums[R]位置上的missing数 = arr[R] - (R + 1)
        // 此时看看还有没有缺的数，k - missing = k - arr[R] + (R + 1)
        // 所以返回的是arr[R] + k - arr[R] + (R + 1) = k + R + 1
        return k + R + 1; // K+L也可以，因为L= R+1
    }
}
