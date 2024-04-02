import java.util.*;

public class tmp {
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new ArrayDeque<>();
        int freshOranges = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }
        int count = 0;
        int[][] DIR = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (freshOranges > 0 && !queue.isEmpty()) {
            count++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int[] cur = queue.poll();
                for (int[] d : DIR) {
                    int x = cur[0] + d[0], y = cur[1] + d[1];
                    if (inArea(x, y, grid) && grid[x][y] == 1) {
                        freshOranges--;
                        grid[x][y] = 2;
                        queue.add(new int[]{x, y});
                    }
                }
            }
        }
        return freshOranges == 0 ? count : -1;
    }

    public boolean inArea(int i, int j, int[][] grid) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }
}
