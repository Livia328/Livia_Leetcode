public class Q892 {
    /*
     * 可以一个tower一个tower的去看（tower means一叠柱体
     * 对于这一个柱体来说，贡献的表面积为：上下两个底面，每个cube的四面
     * 
     * 然后要减去和旁边贴合的，两个柱体贴合的面积是min(height1, height2) * 2
     */
    public int surfaceArea(int[][] grid) {
        int res = 0, n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    res += 2 + grid[i][j] * 4;
                }
                // 减去贴合的面积
                if (i > 0) {
                    res -= Math.min(grid[i][j], grid[i - 1][j]) * 2;
                }
                if (j > 0) {
                    res -= Math.min(grid[i][j], grid[i][j - 1]) * 2;
                }
            }
        }
        return res;
    }
}