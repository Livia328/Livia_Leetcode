import java.util.ArrayDeque;
import java.util.Queue;

public class Q1091 {
    /*
     * 因为是最短路径，所以是bfs
     * 这题里的移动方向是八个方向
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // corner case
        if (grid[0][0] == 1 || grid[m - 1][m - 1] == 1) {
            return -1;
        }
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];
        queue.offer(new int[]{0,0});
        visited[0][0] = true;
        int pathLen = 1;
        int[][] DIRS = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0},
            {1, 1}, {1, -1}, {-1, 1}, {-1,-1}
        };
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                // 如果到达右下角，那么找到路线
                // 且我们用了bfs，所以此时一定是最短路径
                if (cur[0] == m - 1 && cur[1] == n - 1) {
                    return pathLen;
                } 
                for (int[] d : DIRS) {
                    int x = cur[0] + d[0];
                    int y = cur[1] + d[1];
                    // 没越界，为0，且没有被便利过
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0 && !visited[x][y]) {
                        queue.add(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }
            pathLen++;
        }
        return -1;
    }
}
