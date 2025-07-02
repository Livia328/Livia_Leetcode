public class Q867 {
    /*
     * 1 2 3
     * 4 5 6
     * 7 8 9
     * 
     * 他的transpose matrix
     * 1 4 7
     * 2 5 8
     * 3 6 9
     * 
     * 换一下横纵坐标
     * res[j][i] = matrix[i][j];
     */
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[j][i] = matrix[i][j];
            }
        }
        return res;
    }
}
