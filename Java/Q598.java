public class Q598 {
    /*
     * 这道题的本质是寻找范围的交集
     * 比如操作[a, b], [c, d]
     * 操作的交集一定是(min(a, c), min(b,d))
     * 
     * 而这一片区域的元素都会变大
     * 因此我们只要寻找所有元素的交集就行
     */
    public int maxCount(int m, int n, int[][] ops) {
        if (ops == null || ops.length == 0) {
            return m * n;
        }
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;
        for (int[] op : ops) {
            x = Math.min(x, op[0]);
            y = Math.min(y, op[1]);
        }
        return x * y;
    }
}
