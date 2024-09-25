public class Q329 {
    /*
     * dfs + memo
     */
    int[][] memo;
    int[][] DIRS = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public int longestIncreasingPath(int[][] matrix) {
        int res = 0;
        memo = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int cur = dfs(i, j, matrix);
                res = Math.max(res, cur);
            }
        }
        return res;
    }

    public int dfs(int i, int j, int[][] matrix) {
        if (memo[i][j] > 0) {
            return memo[i][j];
        }
        memo[i][j] = 1;
        for (int[] d : DIRS) {
            int x = i + d[0], y = j + d[1];
            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[i][j] < matrix[x][y]) {
                memo[i][j] = Math.max(memo[i][j], 1 + dfs(x, y, matrix));
            }
        }
        return memo[i][j];
    }
}
