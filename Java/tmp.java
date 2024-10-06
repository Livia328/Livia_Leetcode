import java.util.ArrayDeque;
import java.util.Queue;

public class tmp {
    /*
     * 从每一个门开始BFS，找到最近的
     * 把所有的门坐标放入queue中，他们等于说是第0起点
     * 然后开始bfs
     * 
     * 碰到bfs的时候，就把当前门的坐标放进去
     */
    public void wallsAndGates(int[][] rooms) {
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int[][] DIRS = {{-1,0}, {1,0}, {0,-1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : DIRS) {
                int x = cur[0] + d[0], y = cur[1] + d[1];
                // 如果x不在范围内，或者x不是空房间，继续
                if (x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length || rooms[x][y] != Integer.MAX_VALUE) {
                    continue;
                }
                // 新的房间和现在的房间的距离是1，所以就是在新房间的距离上+1
                rooms[x][y] = rooms[cur[0]][cur[1]] + 1;
                queue.offer(new int[]{x, y});
            }
        }
    }
}
