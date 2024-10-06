import java.util.*;

public class Q286 {
    /**
     * You are given an m x n grid rooms initialized with these three possible values.
     * -1 A wall or an obstacle
     * 0 A gate.
     * INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
     * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
     */

     /*
     * 从每一个门开始BFS，找到最近的
     * 把所有的门坐标放入queue中，他们等于说是第0起点
     * 然后开始bfs
     * 
     * 碰到bfs的时候，就把当前门的坐标放进去
     */
    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        int[][] DIR = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0], j = cur[1];
            for (int[] d : DIR) {
                int x = i + d[0], y = j + d[1];
                // 如果在边界，那么返回
                if (x < 0 || x >= m || y < 0 || y >= n || rooms[x][y] != Integer.MAX_VALUE) {
                    continue;
                }
                // otherwise, 根据现在格子里的数，+1
                rooms[x][y] = rooms[i][j] + 1;// 如果[i, j]本来就是门，那么就是1， 也是对的
                queue.offer(new int[]{x, y});
            }
        }
    }
}
