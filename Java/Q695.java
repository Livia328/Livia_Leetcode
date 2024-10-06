public class Q695 {
    /*
     * dfs
     * dfs的用处：把岛淹了，同时记录这个岛的面积
     * 
     * return int，就是岛的面积
     */
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int curArea = dfs(grid, i, j);
                    res = Math.max(curArea, res);
                }
            }
        }
        return res;
    }

    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int dfs(int[][] grid, int i, int j) {
        // base case
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return 0;
        }
        // operation
        // 把当前岛淹了
        grid[i][j] = 2;
        int curArea = 1;
        for (int[] d : dirs) {
            int x = i + d[0];
            int y = j + d[1];
            curArea += dfs(grid, x, y);
        }
        return curArea;
    }
}
