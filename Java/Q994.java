import java.util.*;

public class Q994 {
    /**
     * BFS
     * 其实就是层数吧，要多少层才能把所有橘子遍历完
     */
    public int orangesRotting(int[][] grid) {
        // queue里面放的是橘子的index
        Queue<int[]> queue = new ArrayDeque<>();
        int freshOrgange = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    freshOrgange++;
                }
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int res = 0;
        int[][] DIR = {{1,0}, {0,1}, {-1, 0}, {0, -1}};
        while(freshOrgange > 0 && !queue.isEmpty()) {
            res++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int[] cur = queue.poll();
                for (int[] d : DIR) {
                    int x = cur[0] + d[0];
                    int y = cur[1] + d[1];
                    // 如果是新鲜橙子
                    if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length &&  grid[x][y] == 1) {
                        // 之前的计数-1
                        freshOrgange--;
                        // 变成烂橘子
                        grid[x][y] = 2;
                        // 放入queue里
                        queue.add(new int[]{x, y});
                    }
                }
            }
        }
        // 如果新鲜橙子都变成0了那么返回res，不然就是不能让他们全部腐烂
        return freshOrgange == 0 ? res : -1;
    }
}
