public class Q441 {
    /**
     * brute force， 会TLE
     */
    public static int arrangeCoins1(int n) {
        // find the level
        int sum = 0;
        int res = 0;
        int curLevel = 1;
        while (sum + curLevel <= n) {
            sum += curLevel;
            curLevel += 1;
            res++;
        }
        System.out.println("sum" + sum);
        System.out.println("curLevel" + curLevel);
        // 这样出循环后就是除了素后一行的sum了
        return res;
    }

    /**
     * 二分
     * 可以取的行数在[1, n]之间
     * 
     */
    public int arrangeCoins(int n) {
        long L = 0, R = n;
        while (L <= R) {
            long M = L + (R - L) / 2;
            // 利用等差数列公式，求出前N行的和
            long cur = M * (M + 1) / 2;
            // 当前这行小于N,且下一行加上就大于N了，那这就是答案
            if (cur <= n && cur + M + 1 > n) {
                return (int)M;
            }
            if (cur > n) {
                R = M - 1;
            } else {
                L = M + 1;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        arrangeCoins(8);
    }
}
