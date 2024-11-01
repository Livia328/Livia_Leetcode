import java.util.HashSet;
import java.util.Set;

public class Q694 {
    /*
     * 重点是如何找到distict的岛？什么是这个distinct的标识？
     * 遍历顺序
     * 1234表示上下左右 -1 -2 -3 -4表示撤销
     * 
     * 如果这个遍历顺序是一样的，我们可以认为它是一样的
     * 
     */
    public int numDistinctIslands(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Set<String> islands = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // 淹掉这个岛屿，同时存它的标识
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, 888); // 888是初始，不影响
                    islands.add(sb.toString());
                }
            }
        }
        return islands.size();
    }

    /*
     * 把岛淹了，得到序列化的pattern值
     */
    public void dfs(int[][] grid, int i, int j, StringBuilder sb, int dir) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            return; 
        }
        grid[i][j] = 0;
        sb.append(dir);
        dfs(grid, i - 1, j, sb, 1);
        dfs(grid, i + 1, j, sb, 2);
        dfs(grid, i, j - 1, sb, 3);
        dfs(grid, i, j + 1, sb, 4);
        sb.append(-dir).append(',');
    }
}
