public class Q50 {
    /*
     * 问面试官：n的范围
     * 就是模拟，先排除一些edge case
     * 
     * 如果n是负数，就变成1/x和-n
     */
    public double myPow(double x, int n) {
        if(n == 0) {
            return 1.0;
        }
        if (x == 1.0) {
            return 1.0;
        }
        if (x == -1.0 && n % 2 == 0) {
            return 1.0;
        }
        if (x == -1.0 && n % 2 == 1) {
            return -1.0;
        }
        if (x == 0.0) {
            return 0.0;
        }
        // 注意要变成long，不然可能整形溢出
        long exp = n;
        if (n < 0) {
            x = 1/x;
            exp = -exp;
        }
        double res = 1.0;
        while (exp > 0) {
            // 是奇数的话就乘一个x
            if (exp % 2 == 1) {
                res *= x;
            }
            x *= x;
            exp /= 2;
        }
        return res;
    }
}
