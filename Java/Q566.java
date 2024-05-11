public class Q566 {
    /**
     * 两个数组中，元素的排序是不变的（也就是这是第几个元素）
     * 在res数组中，i * c + j
     * 在mat数组中，i * mat[0].length + j
     * 
     * 所以我们可以遍历0 - m * n然后根据上面的规则寻找对应关系
     */
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int[][] res = new int[r][c];
        int m = mat.length, n = mat[0].length;
        if (m * n != r * c) {
            return mat;
        }
        for (int i = 0; i < m * n; i++) {
            res[i / c][i % c] = mat[i / n][i % n];
        }
        return res;
    }
}
