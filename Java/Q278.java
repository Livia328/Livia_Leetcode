public class Q278 {
    /* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
      // 这个会TLE
    public int firstBadVersio1(int n) {
        while (isBadVersion(n)) {
            n--;
        }
        return n+1;
    }

    /*
     * binary search
     * 
     * 要找到第一个坏的版本
     * 如果M是， M-1不是，返回当前M
     * 
     * 如果M是，缩短右边界，因为我们想看还有没有更小的也是
     * 如果M不是，缩短左边界
     * 
     * [L, R]
     * L <= R, 跳出循环条件： L = R + 1
    */
    public int firstBadVersion(int n) {
        int L = 0, R = n;
        while(L <= R) {
            int M = L + (R - L) / 2;
            if (isBadVersion(M) && !isBadVersion(M - 1)) {
                return M;
            } else if (isBadVersion(M)){
                R = M - 1;
            } else {
                L = M + 1;
            }
        }
        return -1;
    }
}
