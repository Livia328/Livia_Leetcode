public class Q1802 {
    /*
     * brute force
     */
    public int maxValue1(int n, int index, int maxSum) {
        // x是index上的那个数
        for (long x = maxSum; x >= 1; x--) {
            // cal sum
            long sum = x;
            for (int i = index + 1; i < n; i++) {
                sum += Math.max(x - i + index, 1);
            }
            for (int i = index - 1; i >= 0; i--) {
                sum += Math.max(x - index + i, 1);
            }
            if (sum <= maxSum) {
                return (int)x;
            }
        }
        return -1;
    }
    /*
     * 如果我们确定了num[index]的值为x，那么我们就可以得知sum的最小值
     * 因为差的绝对值要<=1，所以我们势必会:[...x-2, x-1, x, x-1, x-2...]
     * 如果有多余的，就填补1
     * 
     * 我们要得到sum尽可能大，所以对x进行二分搜索，
     * 搜索范围[1, maxSum)，
     *  如果min < maxSum,收缩左边界
     *  如果mid == maxSum, 收缩左边界
     *  如果mid > maxSum， 收缩有边界
     * 
     * 我们需要一个额外的函数，来求和getSum(x, len) 
     * 
     */
    public int maxValue2(int n, int index, int maxSum) {
        // [l, r]
        int l = 1, r = maxSum;
        // 跳出循环的条件：[l, l - 1]
        while (l <= r) {
            int m = l + (r - l) / 2;
            long curSum = m + getSum(m, index) + getSum(m, n - index - 1);
            if (curSum > maxSum) {
                // shrink right boundary
                r = m - 1;
            } else if (curSum < maxSum) {
                l = m + 1;
            } else if (curSum == maxSum) {
                l = m + 1;
            }
        }
        return l - 1;
    }

    /*
     *    big......len
     *     
     */
    public long getSum(int biggest, int len) {
        if (len + 1 < biggest) {
            // 不需要补1的情况
            int small = biggest - len;
            return (long)(biggest - 1 + small) * len / 2;
        } else {
            int ones = len - biggest + 1;
            return (long)(biggest - 1) * biggest / 2 + ones;
        }
    }
}
