public class Q7 {
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int cur = x % 10;
            // 判断是否溢出
            if (res < Integer.MIN_VALUE / 10 || res > Integer.MAX_VALUE / 10) {
                return 0;
            }
            res  =  res * 10 + cur;
            x /= 10;
        }
        return res;
    }
}
