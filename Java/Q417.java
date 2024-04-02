import java.util.*;

public class Q417 {
    /**
     * 从终点出发，逆着水流，看经过的路径
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        boolean[][] canReachP = new boolean[m][n];
        boolean[][] canReachA = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(i, 0, canReachP, heights);
            dfs(i, n - 1, canReachA, heights);
        }

        for (int j = 0; j < n; j++) {
            dfs(0, j, canReachP, heights);
            dfs(m - 1, j, canReachA, heights);
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canReachA[i][j] && canReachP[i][j]) {
                    res.add(Arrays.asList(i,j));
                }
            }
        }
        return res;
    }

    static int[][] DIR = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * 
     */
    public void dfs(int i, int j, boolean[][] canReach, int[][] heights) {
        // base case
        if (!inArea(i, j, heights)) {
            return;
        }
        // 如果已经是
        if (canReach[i][j]) {
            return ;
        }
        canReach[i][j] = true;
        for (int[] d : DIR) {
            int x = i + d[0], y = j + d[1];
            // 因为这个条件，让所有进入dfs的格子都是true了
            if (inArea(x, y, heights) && heights[x][y] >= heights[i][j]) {
                dfs(x, y, canReach, heights);
            }
        }
    }

    public boolean inArea(int i, int j, int[][] heights) {
        return i >= 0 && i < heights.length && j >= 0 && j < heights[0].length;
    }
}
