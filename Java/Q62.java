import java.util.Arrays;

public class Q62 {
    /**
     * memo + backtrack
     */
    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];
        return backtrack(m, n, 0, 0, memo);
        
    }

    // 返回[i,j]到[m - 1][n - 1]有多少路
    public int backtrack(int m, int n, int i, int j, int[][] memo) {
        // base case
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return 0;
        }
        if (i == m - 1 && j == n - 1) {
            return 1;
        }
        if (memo[i][j] > 0) {
            return memo[i][j];
        }
        int down = backtrack(m, n, i + 1, j, memo);
        int right = backtrack(m, n, i, j + 1, memo);
        memo[i][j] = down + right;
        return memo[i][j];
    }
}
