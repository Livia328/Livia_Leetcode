public class Q338 {
    /**
     * 题解参考：https://leetcode.cn/problems/counting-bits/solutions/7882/hen-qing-xi-de-si-lu-by-duadua
     * 
     * 
     * 世界上的数只有奇数和偶数
     * 奇数，一定是前面偶数加一个1,多的就是后面加上的1
     * 0:00 1:01
     * 2:10 3:11
     * 
     * 偶数，一定和它减半的是一样的，因为只是在最后加了一个0
     * 2:10 4:100 8:1000
     * 3:11 6:110 12:1100
     * 
     */
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        res[0] = 0;
        for(int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                res[i] = res[i / 2];
            } else {
                res[i] = res[i / 2] + 1;
            }
        }
        return res;
    }
}
