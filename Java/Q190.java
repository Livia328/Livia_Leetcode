public class Q190 {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        if (n == 0) return 0;
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1; // 向左移动一位
            if((n & 1) == 1) { // 如果n的最后一位是1
                res++;
            }
            n >>= 1; // n向右一位
        }
        return res;
    }
}
