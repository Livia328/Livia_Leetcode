public class Q63 {
    /*
     * 带memo的dp
     * 
     */
    int[][] memo;
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        memo = new int[m][n];
        return dp(obstacleGrid, m - 1, n - 1);
    }

    /*
     * dp函数的含义：从grid[0][0]出发到grid[i][j]的路径条数为dp(i, j)
     */
    public int dp(int[][] obstacleGrid, int i, int j) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        // base case
        // 越界或者遇到障碍
        if (i < 0 || i >= m || j < 0 || j >= n || obstacleGrid[i][j] == 1) {
            return 0;
        }
        // 是起点的话
        if (i == 0 && j == 0) {
            return 1;
        }
        // check memo
        if (memo[i][j] > 0) {
            return memo[i][j];
        }
        // 到达grid[i][j]的数量为到达grid[i - 1][j]的数量+到达grid[i][j - 1]的数量
        int left = dp(obstacleGrid, i - 1, j);
        int up = dp(obstacleGrid, i, j - 1);
        memo[i][j] = left + up;
        return memo[i][j];
    }

    /*
     * dp[i][j] = dp[i - 1][j] + dp[i] [j - 1]
     * 但是需要偏移一位，因为要有basecase
     * dp[0][...]和dp[...][0]表示之外的
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m + 1][n + 1];
        // 如果第一个格子是障碍物的话就是0，否则就是1条
        dp[1][1] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }
                // 如果是障碍物就跳过
                if (obstacleGrid[i - 1][j - 1] == 1) {
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    /*
     * 将二位数组优化成一位数组
     * 我们更新dp[i][j]只依赖于dp[i - 1][j]和dp[i][j - 1]的状态
     * 
     * 压缩到一维数组里
     * dp[i - 1][j]的值落到dp[j]上，dp[i][j - 1]的值落到dp[j - 1]上
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n + 1];
        dp[1] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }
                if (obstacleGrid[i - 1][j - 1] == 1) {
                    dp[j] = 0;
                    continue;
                }
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n];
    }
}
