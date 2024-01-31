public class Q875 {
    /**
     * find the min speed when she can finsh all bananas
     * find the left boundary
     * 
     * the range of binary search is [1, Max(piles)]
     * 
     * 注意事项：
     * 1.双闭区间记得从[1】开始，不然中间就要divide by 0了
     * 2.双闭区间记得是long，不然会越界
     */
    public int minEatingSpeed(int[] piles, int h) {
        long R = 0;
        for (int p : piles) {
            R = Math.max(R, p);
        }
        // [L, R]
        long L = 1;
        while (L <= R) {
            long M = L + (R - L) / 2;
            long curTime = getEatTime(M, piles);
            // shrink the right boundary
            if (curTime > h) {
                // shrink the left boundary
                L = M + 1;
            } else if (curTime == h) {
                // shrink the right boundary
                // because we want to see whether there are smaller
                R = M - 1;
            } else if (curTime < h) {
                // shrink the right boundary
                R = M - 1;
            }
        }
        return (int)L;
    }
    
    /**
     * given the speed s, and the piles
     * return the time needed to finishi piles
     */
    private long getEatTime(long s, int[] piles) {
        long res = 0;
        for (int p : piles) {
            if (p < s) {
                res++;
            } else {
                res += p / s;
                if (p % s != 0) {
                    res++;
                }
            }
        }
        return res;
    }

    private int getEatTime2(int s, int[] piles) {
        int res = 0;
        for (int p : piles) {
            res += p / s;
            if (p % s != 0) {
                res++;
            }
        }
        return res;
    }

    // 写一个左闭右开区间的
    // [L, R)
    public int minEatingSpeed2(int[] piles, int h) {
        // 这个R是根据题目条件给定的
        int L = 1, R = 1000000001;
        // 因为右边是开区间，结束的条件是L = R
        while (L < R) {
            int M = L + (R - L) / 2;
            int curTime = getEatTime2(M, piles);
            if (curTime > h) {
                // shrink the left boundary
                L = M + 1;
            } else if (curTime < h) {
                // shrink the right boundary
                R = M;
            } else if (curTime == h) {
                R = M;
            }
        }
        return L;
    }
}
