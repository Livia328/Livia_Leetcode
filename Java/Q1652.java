public class Q1652 {
    /*
     * 如果k = 0
     * 直接return 全0
     * 
     * 如果k > 0
     * sliding window
     * 
     * initial window
     * [1, 2, 3, 4] k = 2
     *     L  R
     * 
     * 每次从window种加入为[L % code.length]
     * 减去[R % code.length]
     * 
     * 如果k < 0
     * 只要更换初始window的位置就行
     * [1, 2, 3, 4] k = -2
     *        L  R
     * 
     * R = code.length - 1
     * L = code.length - k
     */
    public int[] decrypt(int[] code, int k) {
        int[] res = new int[code.length];
        if (k == 0) {
            return res;
        }
        // initial window
        int L = 1, R = k, sum = 0;
        if (k < 0) {
            k = -k;
            L = code.length - k;
            R = code.length - 1;
        }
        // 先把数放进来
        // 现在sum里就是第一个window，左右都包含的和
        for (int i = L; i <= R; i++) {
            sum += code[i];
        }
        res[0] = sum;
        for (int i = 1; i < code.length; i++) {
            sum -= code[(L++) % code.length];
            sum += code[(++R) % code.length];
            res[i] = sum;
        }
        return res;
    }
}
