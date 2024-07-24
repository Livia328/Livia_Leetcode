public class Q883 {
    public int projectionArea(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            int x = 0, y = 0;
            for (int j = 0; j < grid[0].length; j++) {
                x = Math.max(x, grid[i][j]);
                y = Math.max(y, grid[j][i]);
                if (grid[i][j] != 0) { // xy panel
                    res++;
                }
            }
            res += x + y;
        }
        return res;
    }
}
