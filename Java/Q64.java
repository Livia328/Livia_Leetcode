import java.util.Arrays;

public class Q64 {
    /**
     * backtrack会超时
     * 
     * backtrack + memo就OK
     */
    public int minPathSum(int[][] grid) {
        // backtrack
        // return backtrack(grid, 0, 0);

        // memo + backtrack
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int[][] memo = new int[grid.length][grid[0].length];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return backtrack2(grid, 0, 0, memo);
    }

    public int backtrack(int[][] grid, int i, int j) {
        // base case
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return Integer.MAX_VALUE;
        }
        // base case
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return grid[i][j];
        }
        // 朝下走
        int down = backtrack(grid, i + 1, j);
        int right = backtrack(grid, i, j + 1);
        int res = Math.min(down, right) + grid[i][j];
        return res;
    }

    /**
     * memo + backtrack
     */
    public int backtrack2(int[][] grid, int i, int j, int[][] memo) {
        // base case
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return Integer.MAX_VALUE;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // base case
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return grid[i][j];
        }
        // 朝下走
        int down = backtrack2(grid, i + 1, j, memo);
        int right = backtrack2(grid, i, j + 1, memo);
        memo[i][j] = Math.min(down, right) + grid[i][j];
        return memo[i][j];
    }
}
