public class Q59 {
    /*
     * 确认上下左右边界，往里缩
     * 
     * l     r
     * 1  2  3 upper
     * 4  5  6
     * 7  8  9 botton
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int upper = 0, lower = n - 1;
        int left = 0, right = n - 1;
        int count = 1;
        while (count <= n * n) {
            // 从左向右填满upper
            if (upper <= lower) {
                for(int j = left; j <= right; j++) {
                    res[upper][j] = count++;
                }
                upper++;
            }
            // 填满右边
            if (left <= right) {
                for (int i = upper; i <= lower; i++) {
                    res[i][right] = count++;
                }
                right--;
            }
            // 填满最下面一行
            if (upper <= lower) {
                for(int j = right; j >= left; j--) {
                    res[lower][j] = count++;
                }
                lower--;
            }
            // 填满左边
            if (left <= right) {
                for (int i = lower; i >= upper; i--) {
                    res[i][left] = count++;
                }
                left++;
            }
        }
        return res;
    }
}
