public class Q1314 {
    /*
     * Input: mat = [
     *    x1x2
     * [1,2,3],y1
     * [4,5,6],y2
     * [7,8,9]], k = 1
     * 
     * 
     * Output: [
     * [12,21,16],
     * [27,45,33],
     * [24,39,28]]
     * 
     * 第一个格子：[0,0][0,1][1,0],[1,1]
     * 
     * 可以用presum，
     * 如果我们想计算sum[x1, y1, x2, y2]
     * 那么它可以由[0,0,x2,y2] - [0,0,x1 - 1,y2] - [0,0,x2, y1-1] + [0,0,x1-1, y1-1]
     * 
     * 所以我们可以用presum来计算[0,0,x1,y1]的结果，这样就会方便很多
     * 
     * 可以抽象出一个函数来计算presum
     * 一个函数来返回某个region的值
     * 
     * 在这个函数里，遍历每一个cell
     */
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] preSum = getPreSum(mat);
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x1 = Math.max(i - k, 0); // 为了防止越界
                int y1 = Math.max(j - k, 0);
                int x2 = Math.min(i + k, m - 1);
                int y2 = Math.min(j + k, n - 1);

                res[i][j] = sumReigon(preSum, x1, x2, y1, y2);
            }
        }
        return res;
    }

    /*
     * presum表示记录 matrix 中子矩阵 [0, 0, i-1, j-1] 的元素和
     */
    public int[][] getPreSum(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] preSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j-1]+mat[i - 1][j-1] - preSum[i - 1][j-1];
            }
        }
        return preSum;
    }

    /*
     * 因为presum表示记录 matrix 中子矩阵 [0, 0, i-1, j-1] 的元素和
     * 所以需要-1
     */
    public int sumReigon(int[][] preSum, int x1, int x2, int y1, int y2) {
        return preSum[x2 + 1][y2 + 1] - preSum[x1][y2 + 1] - preSum[x2 + 1][y1] + preSum[x1][y1];
    }
}
