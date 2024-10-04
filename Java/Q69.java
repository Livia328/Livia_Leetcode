public class Q69 {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        long l = 1, r = x / 2;
        // [l, r] 跳出条件l = r - 1
        long ans = 0;
        while (l <= r) {
            long m = l + (r - l) / 2;
            long mul = m * m;
            if (mul == x) {
                return (int)m; 
            } else if (mul < x) {
                ans = m; //因为乘积比x小，所以m可能是答案，但还要尝试更大的数
                l = m + 1;
            } else if (mul > x) {
                r = m - 1; // 因为乘积比x大，所以m不可能是答案
            }
        }
        return (int)ans;
    }
}
