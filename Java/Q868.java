public class Q868 {
    public int binaryGap(int n) {
        int res = 0;
        int cur = 0;
        int prv = -1;
        while (n != 0) {
            cur++;
            if ((n & 1) == 1) {// last digit is 1
                if(prv != -1) {
                    res = Math.max(res, cur - prv);
                }
                prv = cur;
            }
            n >>= 1;
        }
        return res;
    }
}
