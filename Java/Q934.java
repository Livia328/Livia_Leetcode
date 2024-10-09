import java.util.*;

public class Q934 {
    /*
     * 因为是要找从一个岛到另一个岛的最短距离
     * 所以很容易想到bfs
     * 
     * 先用dfs找到第一个岛，把第一个岛变成2，用来区分第一个岛和第二个岛
     * 然后把第一个岛周围的海水加入queue，为bfs做准备
     * 
     * bfs从上面dfs中加入的海水格子出发，搜到第二个岛为止
     */
    int[][] DIRS = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public int shortestBridge(int[][] grid) {
        // 先dfs
        // 一定要加这个，不然跳不出两层循环
        // 可以写到下面循环的时候再回来加
        boolean find1stIsland = false; 
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            if (find1stIsland) break;
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, queue);
                    // 一定要加break， 不然我们就会连着第二个岛一起dfs了
                    find1stIsland = true;
                    break;
                }
            }
        }
        // bfs
        int step = 0;
        while (!queue.isEmpty()) {
            // 向外扩展
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();   
                for (int[] d : DIRS) {
                    int x = cur[0] + d[0];
                    int y = cur[1] + d[1];
                    // 周围的格子也是海水
                    if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 0) {
                        grid[x][y] = 2;
                        queue.offer(new int[]{x, y});
                    } else if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) { // 找到了另一个岛
                        return step;
                    }
                }
            }
        }
        return step;
    }

    /*
     * 把第一个岛从1变成2
     * 将边缘的海水格子加入queue
     */
    public void dfs(int[][] grid, int i, int j, Queue<int[]> queue) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 2) {
            return;
        }
        // 是第一个岛周围的一个海水格子
        // 把它变成岛1，加入queue
        if (grid[i][j] == 0) {
            grid[i][j] = 2;
            queue.offer(new int[]{i, j});
            return;
        }
        // 否则就是岛1
        // 把1变成2
        grid[i][j] = 2;
        for (int[] dir : DIRS) {
            dfs(grid, i + dir[0], j + dir[1], queue);
        }
    }
}
