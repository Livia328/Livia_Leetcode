public class Q461 {
    public int hammingDistance1(int x, int y) {
        // xor之后相同的会变成0，不相同的会变成1
        return Integer.bitCount(x ^ y);
    }


    /**
     * 
     * 用移位操作，计数
     * 记 s=x^y，我们可以不断地检查 sss 的最低位，如果最低位为 111，那么令计数器加一，
     * 然后我们令 sss 整体右移一位，这样 sss 的最低位将被舍去，
     * 原本的次低位就变成了新的最低位。
     * 我们重复这个过程直到 s=0s=0s=0 为止。
     * 这样计数器中就累计了 sss 的二进制表示中 111 的数量。
     * 
     * 
     * 关于s & 1
     * Binary of 4: 100
     * Binary of 1: 001
     * Result of 100 & 001: 000
     * 
     * 所以&的本质就只有最后一位，所以可以检查最后一位是0还是1
     */
    public int hammingDistance(int x, int y) {
        int s = x ^ y;
        int res = 0;
        while (s != 0) {
            res += s & 1; // 检查最后一位是不是1
            s >>= 1;//将s向右移动一位，也就是在前面加0，舍弃掉最后一位
        }
        return res;
    }
}