import java.util.*;

public class tmp {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    int[][] DIR = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }
        if (grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        for (int[] d : DIR) {
            int x = i + d[0], y = j + d[1];
            dfs(grid, x, y);
        }
    }
}
