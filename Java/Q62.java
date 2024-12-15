import java.util.Arrays;

public class Q62 {
    /**
     * memo + dfs
     * 
     * 用dfs(x, y)表示从(0,0)到（x,y）有多少path
     * 因为只能向下和向右走
     * 所以取决于dfs(x - 1, y) + dfs(x, y - 1)
     */
    public int uniquePaths1(int m, int n) {
        int[][] memo = new int[m][n];
        return dfs(m - 1, n - 1, memo);
    }

    // 返回[i,j]到[m - 1][n - 1]有多少路
    public int dfs(int x, int y, int[][] memo) {
        // base case
        if (x == 0 && y == 0) {
            return 1;
        }
        if (x < 0 || y < 0) {
            return 0;
        }
        if (memo[x][y] > 0) {
            return memo[x][y];
        }
        memo[x][y] = dfs(x - 1, y, memo) + dfs(x, y - 1, memo);
        return memo[x][y];
    }

    /*
     * 因为只能向右和向下
     * 所以每个格子的path依赖于它上面的格子和左边的格子
     * 显然是一个dp问题
     * 
     * 所以我们可以直接用dp
     * dp[i][j]表示到达(i, j)的路径
     * 
     * 递推公式: dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
     * 
     * 根据递推公式，我们应该是从上到下从左到右便利
     * 并且要set base case
     * 
     * 第一行和第一列都为1，因为在边界，只有一种到达方式
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
